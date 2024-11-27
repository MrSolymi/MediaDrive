import type { ProfileInfo } from "./types"

export interface ApiError {
    message: string
    details: Array<any>
    error: {
        statusCode: number
        statusPhrase: string
    }
}

export interface LoginRequest {
    username: string
    password: string
}

export interface LoginResponse {
    token: string
}

export type BackendRequest = LoginRequest | null

export type BackendResponse = LoginResponse | ProfileInfo