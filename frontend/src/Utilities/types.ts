export type UserProfileOutput = {
    username: string,
    role: Roles
    lastOnline?: string,
    accCreated?: string,
}

export const RoleValues = {
    USER: 'USER',
    GUEST: 'GUEST',
} as const;

export type Roles = typeof RoleValues[keyof typeof RoleValues];

export type UserCredentials = {
    email: string,
    username: string,
}

export type GlobalErrorState = {
    status: number | null;
    message: string | null;
}

export type ActivityState = {
    year: number | undefined,
    days_minutes: [],
    month_masks: []
}