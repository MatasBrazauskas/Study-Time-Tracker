import { configureStore } from "@reduxjs/toolkit";

import userReducer from './usersStore';
import errorsReducer from './errorsStore';
import activityReducer from './activityStore';

const store = configureStore({
    reducer: {
        USERS_DATA_SLICES_NAME: userReducer,
        ERRORS_SLICES_NAME: errorsReducer,
        ACTIVITY_SLICES_NAME: activityReducer,
    }
});

export default store;
export type AppStore = typeof store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;