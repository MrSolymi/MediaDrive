import type { ApiError, BackendRequest, BackendResponse } from './apiTypes';

export async function useApi(
	token: string | null = null,
	endpoint: string,
	method: string,
	payload: BackendRequest | null = null
): Promise<BackendResponse | ApiError> {
	const settings: RequestInit = {
		method: method
	};
	if (payload !== null) settings['body'] = JSON.stringify(payload);

	const headers: any = {
		'Content-Type': 'application/json'
	};
	if (token !== null) {
		headers['Authorization'] = 'Bearer ' + token;
	}
	settings.headers = headers;

	const response = fetch('http://localhost:8080' + endpoint, settings);
	return (await response).json();
}
