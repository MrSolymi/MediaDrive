// eslint-disable-next-line @typescript-eslint/no-explicit-any
export function isError(response: any): boolean {
    return 'message' in response && 'details' in response && 'error' in response
}

export function isTokenExpiredError(response: any): boolean {
    return response.error === 'Unauthorized'
}

export interface ProfileInfo {
    username: string
    email: string
    role: string
}