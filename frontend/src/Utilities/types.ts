export type UsersInformation = {
    username: string,
    role: 'USER' | 'GUEST' | 'ADMIN',
    lastOnline: string,
    accCreated: string,
}