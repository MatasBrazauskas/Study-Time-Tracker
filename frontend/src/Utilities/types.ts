export type UsersInformation = {
    username: string,
    role: 'USER' | 'GUEST' | 'ADMIN',
    lastOnline: string,
    accCreated: string,
}

export type UsersInfo = {
    email: string,
    username: string,
}

export type GlobalErrorState = {
    status: number | null;
    message: string | null;
}