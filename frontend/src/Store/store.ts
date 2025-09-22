import { configureStore } from "@reduxjs/toolkit";

import userReducer from './usersDataStore';

const store = configureStore({
    reducer: {
        USERS_DATA_SLICES_NAME: userReducer,
    }
});

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;