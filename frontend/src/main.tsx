import { lazy, StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Outlet, Routes, Route } from 'react-router-dom';

import { Provider } from 'react-redux';
import store from './Store/store';

import { GOOGLE_CLIENT_ID } from './Utilities/const';
import './main.css';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

export const queryClient = new QueryClient({
  defaultOptions:{
    queries:{
      staleTime: 60 * 5,
    }
  }
});

import { setUpInterceptors } from './Utilities/fetchSettings';

setUpInterceptors(store);

const LogInPage = lazy(() => import('./LoginPage'));
const UserActivityPage = lazy(() => import('./Activity/UsersActivityPage'));
const ClockPage = lazy(() => import('./Clock/ClockPage'));
import TopBar from './TopBarPage';

function MainPage(){

  return (
    <div>
      <TopBar />
      <Outlet />
    </div>
  )
}


function App(){

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<MainPage/>}>
            <Route path='login' element={<LogInPage/>}/>
            <Route path='activity' element={<UserActivityPage />}/>
            <Route path='clock' element={<ClockPage />}/>
          </Route>
        </Routes>
      </BrowserRouter>
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
