import { superValidate } from 'sveltekit-superforms'
import type { Actions, PageServerLoad } from './$types'
import { zod } from 'sveltekit-superforms/adapters'
import { loginSchema } from '$lib/schema'
import { fail } from '@sveltejs/kit'
import { redirect } from '@sveltejs/kit'

export const load: PageServerLoad = async () => {
    return {
        form: await superValidate(zod(loginSchema))
    }
}

export const actions: Actions = {
    default: async ({ request }) => {
        const form = await superValidate(request, zod(loginSchema))
        if (!form.valid) {
            return fail(400, { form })
        }

        const reqData = {
            username: form.data.username,
            password: form.data.password
        }

        //console.log(JSON.stringify(reqData))

        const data = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            body: JSON.stringify(reqData),
            headers: {
                'Content-Type': 'application/json'
            }
        })

        //await(data.json())

        let text = await data.text()

        //console.log(data.body)
        console.log(text)
        console.log(data.status)

        if (data.status === 200) {
            throw redirect(303, '/dashboard')
        }
    }
}
