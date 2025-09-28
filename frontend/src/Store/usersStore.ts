import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { USERS_DATA_SLICES_NAME } from "../Utilities/const";
import { type UserProfileOutput, RoleValues} from "../Utilities/types";

const initialState: UserProfileOutput= {
    username: 'GUEST',
    role: RoleValues.GUEST,
}

const usersSlice = createSlice({
    name: USERS_DATA_SLICES_NAME,
    initialState,
    reducers: {
        clearUsersInfo: () => {
            return initialState;
        },
        setUsersInfo: (_: UserProfileOutput, action: PayloadAction<UserProfileOutput>) => {
            return action.payload;
        }
    }
});

export const { clearUsersInfo, setUsersInfo } = usersSlice.actions;
export default usersSlice.reducer;