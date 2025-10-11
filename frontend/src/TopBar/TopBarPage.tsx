import { useQuery} from "@tanstack/react-query";
import { useDispatch, useSelector } from "react-redux";
import type { RootState } from "../Store/store";
import { getUsersProfile } from "../APIs/userProfileAPIs";
import { setUsersInfo } from "../Store/usersStore";
import { useNavigate } from "react-router-dom";

import { RoleValues} from "../Utilities/types";
import { USER_PROFILE_QUERY_KEY } from "../Utilities/const";
import { deleteUser } from "../APIs/userProfileAPIs";

function TopBar(){

    const usersProfile = useSelector((state: RootState) => state.USERS_DATA_SLICES_NAME);
    const errors = useSelector((state: RootState) => state.ERRORS_SLICES_NAME);

    const dispatch = useDispatch();
    const navigator = useNavigate();


    useQuery({
        queryFn: async() => {
            const usersData = await getUsersProfile();
            dispatch(setUsersInfo(usersData));

            switch(usersData.role){
                case RoleValues.GUEST:
                    navigator('/login')
                    break;
                case RoleValues.USER:
                    navigator('/main')
                    break;
            }

            return usersData;
        },
        queryKey: [USER_PROFILE_QUERY_KEY],
        staleTime: Infinity,
    });

    const delUser = async () => {
        await deleteUser();
    }

    return (
        <div className="container">
            {errors.message && 
            <div className='error-box'>
                <div>Message : {errors.message}</div>
                <div>Status : {errors.status}</div>
            </div>
            }

            <div>{usersProfile.username}</div>
            {usersProfile.lastOnline && <div>{usersProfile.lastOnline}</div>}

            <div className = 'actions'>
                <button onClick={() => delUser()}>Delete User</button>
            </div>
        </div>
    )
}

export default TopBar;