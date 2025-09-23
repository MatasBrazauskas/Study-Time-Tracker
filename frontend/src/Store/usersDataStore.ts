import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { USERS_DATA_SLICES_NAME } from "../Utilities/const";
import { type UsersInformation } from "../Utilities/types";

const initialState:UsersInformation = {
    username: 'GUEST',
    role: 'GUEST',
    lastOnline: new Date().toLocaleDateString('en-CA'),
    accCreated: new Date().toLocaleDateString('en-CA'), 
}

const usersSlice = createSlice({
    name: USERS_DATA_SLICES_NAME,
    initialState,
    reducers: {
        clearUsersInfo: () => {
            return initialState;
        },
        updateUsersInfo: (state: UsersInformation, action: PayloadAction<UsersInformation>) => {
            state.username = action.payload.username;
            state.role = action.payload.role;
            state.lastOnline = action.payload.lastOnline;
            state.accCreated = action.payload.accCreated;
            // return action.payload;
        }
    }
});

export const { clearUsersInfo, updateUsersInfo } = usersSlice.actions;
export default usersSlice.reducer;