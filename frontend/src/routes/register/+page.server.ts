import { redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import { actionResult, setError, superValidate } from 'sveltekit-superforms';
import { zod } from 'sveltekit-superforms/adapters';
import { loginSchema, registerSchema } from '$lib/schema';
import { useApi } from '$lib/api';
import { isError } from '$lib/types';
import type { ApiError, RegisterRequest, RegisterResponse } from '$lib/apiTypes';

export const load: PageServerLoad = async ({ cookies }) => {
	// already logged in, straight to dashboard
	const token = cookies.get('token');
	if (token !== undefined) throw redirect(307, '/dashboard');

	return {
		form: await superValidate(zod(loginSchema))
	};
};

export const actions: Actions = {
	default: async ({ request }) => {
		const form = await superValidate(request, zod(registerSchema));
		if (!form.valid) {
			actionResult('failure', { form }, 400);
		}

		const reqData: RegisterRequest = {
			username: form.data.username,
			email: form.data.email,
			password: form.data.password,
			passwordConfirm: form.data.passwordConfirm,
			inviteToken: form.data.inviteToken
		};

		const data = await useApi(null, '/api/auth/register', 'POST', reqData);

		if (isError(data)) {
			const error = data as ApiError;

			return setError(form, 'username', error.message);
		} else {
			const result = data as RegisterResponse;

			throw redirect(302, '/login');
		}
	}
};
