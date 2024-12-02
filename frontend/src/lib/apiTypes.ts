import type { ProfileInfo } from './types';

export interface ApiError {
	message: string;
	details: Array<any>;
	error: {
		statusCode: number;
		statusPhrase: string;
	};
}

export interface LoginRequest {
	username: string;
	password: string;
}

export interface LoginResponse {
	token: string;
}

export interface RegisterRequest {
	username: string;
	email: string;
	password: string;
	passwordConfirm: string;
	inviteToken: string;
}

export interface RegisterResponse {
	status: string;
}

export type BackendRequest = LoginRequest | RegisterRequest | null;

export type BackendResponse = LoginResponse | RegisterResponse | ProfileInfo;
