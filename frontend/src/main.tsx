import { lazy, StrictMode, useEffect } from 'react'
import { createRoot } from 'react-dom/client'

import { Provider, useSelector } from 'react-redux';
import store, { type RootState } from './Store/store';

const LogIn = lazy(() => import('./Login'));
import { GOOGLE_CLIENT_ID } from './Utilities/const';
import './main.css';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

export const queryClient = new QueryClient();

import { axiosDefaultSetUp } from './Utilities/const';
import getCookies from './APIs/getCookies';

function App(){

  const usersData = useSelector((state: RootState) => state.USERS_DATA_SLICES_NAME);

  useEffect(() => {
    const idk = async () => {
      await getCookies();
    }
    axiosDefaultSetUp();
    idk();
  }, []);

  return (
    <div>
      <LogIn />
      <div>{usersData.username}</div>
      <div>{usersData.role}</div>
    </div>
  )
}

createRoot(document.getElementById('root')!).render(
  <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
    <QueryClientProvider client={queryClient}>
      <StrictMode>
        <Provider store={store}>
          <App />
        </Provider>
      </StrictMode>
    </QueryClientProvider>
  </GoogleOAuthProvider>
)
