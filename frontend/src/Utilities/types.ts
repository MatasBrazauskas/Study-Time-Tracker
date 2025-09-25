export type UserProfileOutput = {
    username: string,
    role: 'USER' | 'GUEST' | 'ADMIN',
    lastOnline: string,
    accCreated: string,
}

export type UserCredentials = {
    email: string,
    username: string,
}

export type GlobalErrorState = {
    status: number | null;
    message: string | null;
}