import { GoogleLogin, type CredentialResponse } from "@react-oauth/google";
import { jwtDecode } from "jwt-decode";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

import { LogInUser, RegisterUser } from "./APIs/userProfileAPIs";
import { setUsersInfo } from "./Store/usersStore";
import useCycle from "./Hooks/useCycle";

function LogIn()
{
    const navigation = useNavigate();
    const dispatch = useDispatch();

    const {apiFunction, mode, increment} = useCycle([{fn: LogInUser}, {fn: RegisterUser}], ['Log In', 'Register']);

    const apiCall = async (obj: CredentialResponse) => {
        const decodedObject: any = jwtDecode(obj.credential!);
        const usersInfo: Pick<typeof decodedObject, "email" | "username"> = {
            email: decodedObject.email!,
            username: decodedObject.name!,
        }
        
        const data = await apiFunction(usersInfo);

        dispatch(setUsersInfo(data));
        navigation('/activity')
    }

    return (
        <div>
            {<div>Current mode: {mode}</div>}
            <GoogleLogin onSuccess={(e) => apiCall(e)} />
            <button onClick={() => increment()}>Swithc API call method</button>
        </div>)
};

export default LogIn;