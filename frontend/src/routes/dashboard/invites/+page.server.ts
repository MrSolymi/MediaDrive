import { error, redirect } from "@sveltejs/kit";
import type { PageServerLoad } from "./$types";
import { useApi } from "$lib/api";
import { isTokenExpiredError } from "$lib/types";
import type { AllInvitesResponse, DeleteInviteTokenRequest } from "$lib/apiTypes";

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

    const resDataInvites = await useApi(token as string, '/api/invite/all_invites', 'POST');

    //console.log(resDataInvites as AllInvitesResponse);

    return {
        invitesData: resDataInvites as AllInvitesResponse
    }
}

export const actions = {
    default: async ({ request, cookies }) => {
        const token = cookies.get('token');
        if (token === undefined) throw redirect(307, '/login');

        const resData = await useApi(token as string, '/api/profile/info', 'GET');

        if (isTokenExpiredError(resData)) {
            cookies.delete('token', { path: '/' });

            throw redirect(307, '/login');
        }


        const tokenId = (await request.formData()).get('tokenId')
        if (!tokenId) error(404, 'User not found')


        const reqData: DeleteInviteTokenRequest = {
            token: tokenId.toString()
        }
        
        useApi(null, '/api/invite/delete', 'POST', reqData );
    }
}
