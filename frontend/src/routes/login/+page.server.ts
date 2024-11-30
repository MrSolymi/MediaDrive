import { actionResult, setError, superValidate } from 'sveltekit-superforms'
import type { Actions, PageServerLoad } from './$types'
import { zod } from 'sveltekit-superforms/adapters'
import { loginSchema } from '$lib/schema'
import { redirect } from '@sveltejs/kit'
import { isError } from '$lib/types'
import type { ApiError, LoginResponse } from '$lib/apiTypes'
import { useApi } from '$lib/api'

export const load: PageServerLoad = async ({ cookies }) => {
    // already logged in, straight to dashboard
    const token = cookies.get('token')
    if (token !== undefined) throw redirect(307, '/dashboard')

    return {
        form: await superValidate(zod(loginSchema))
    }
}

export const actions: Actions = {
    default: async ({ request, cookies }) => {
        const form = await superValidate(request, zod(loginSchema))
        if (!form.valid) {
            return actionResult('failure', { form }, 400)
        }

        const reqData = {
            username: form.data.username,
            password: form.data.password
        }
        const data = await useApi(null, '/api/auth/login', 'POST', reqData)

        //console.log(data)

        if (isError(data)) {
            const error = data as ApiError
            
            return setError(form, 'username', error.message)
        } else {
            const result = data as LoginResponse

            //console.log(result)
            cookies.set('token', result.token, { path: '/', httpOnly: false })
            console.log(cookies.get('token'))
            throw redirect(302, '/dashboard')
        }
    }
}
