import { redirect } from "@sveltejs/kit";
import type { PageServerLoad } from "./$types";
import { useApi } from "$lib/api";
import { isTokenExpiredError, type ProfileInfo } from "$lib/types";
import type { AllUserResponse } from "$lib/apiTypes";

export const load: PageServerLoad = async ({ cookies }) => {
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

    const resDataUsers = await useApi(token as string, '/api/profile/all_users', 'GET');

    return {
        usersData: resDataUsers as AllUserResponse
    }
};