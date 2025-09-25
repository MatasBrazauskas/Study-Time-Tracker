import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { type GlobalErrorState } from "../Utilities/types";
import { ERRORS_SLICES_NAME } from "../Utilities/const";

const initialState: GlobalErrorState = {
    status: null,
    message: null,
}

const errorSlice = createSlice({
    name: ERRORS_SLICES_NAME,
    initialState,
    reducers: {
        setErrors: (_: GlobalErrorState, action: PayloadAction<GlobalErrorState>) => {
            return action.payload;
        },
        clearErrors: () => {
            return initialState;
        }
    }
});

export const { setErrors, clearErrors} = errorSlice.actions;
export default errorSlice.reducer;