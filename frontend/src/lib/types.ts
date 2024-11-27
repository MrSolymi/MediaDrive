// eslint-disable-next-line @typescript-eslint/no-explicit-any
export function isError(response: any): boolean {
    return 'message' in response && 'details' in response && 'error' in response
}

export interface ProfileInfo {
    username: string
    email: string
    role: string
}