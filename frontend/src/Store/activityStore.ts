import { createSlice, type PayloadAction } from "@reduxjs/toolkit"
import { ACTIVITY_SLICES_NAME } from "../Utilities/const";
import { type ActivityState } from "../Utilities/types";

const initialState: ActivityState = { 
    year: undefined,
    days_minutes: [],
    month_masks: []
}

const activitySlice = createSlice({
    name: ACTIVITY_SLICES_NAME,
    initialState,
    reducers: {
        resetActivityState: () => {
            return initialState;
        },
        setActivityState: (_: ActivityState, action: PayloadAction<ActivityState>) => {
            return action.payload;
        }
    }
});

export const { resetActivityState, setActivityState } = activitySlice.actions;
export default activitySlice.reducer;