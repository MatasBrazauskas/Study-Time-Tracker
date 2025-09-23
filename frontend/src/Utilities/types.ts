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