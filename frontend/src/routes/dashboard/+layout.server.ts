import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad } from './$types';
import { useApi } from '$lib/api';
import { isTokenExpiredError, isError, type ProfileInfo } from '$lib/types';

export const load: LayoutServerLoad = async ({ cookies }) => {
	const token = cookies.get('token');
	if (token === undefined) throw redirect(307, '/login');

	const resData = await useApi(token as string, '/api/profile/info', 'GET');

	//console.log(resData);

	//console.log('error' in resData);

	if (isTokenExpiredError(resData)) {
		// invalid authentication

		cookies.delete('token', { path: '/' });

		throw redirect(307, '/login');
	}

	const profileData = resData as ProfileInfo;

	return {
		profileData
	};
};
