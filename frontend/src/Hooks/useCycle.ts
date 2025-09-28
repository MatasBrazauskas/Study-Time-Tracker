import { useState, useReducer } from 'react';

import type { UserProfileOutput } from '../Utilities/types';
import { type UserCredentials } from '../Utilities/types';

type CycleItem = {
    fn: (info: UserCredentials) => Promise<UserProfileOutput>;
}

function useCycle(fnArr: CycleItem[], modesArr: string[]){
    const [arrState] = useState<CycleItem[]>(fnArr);
    const [modes] = useState<string[]>(modesArr);

    const [index, dispatch] = useReducer((state: number) => {
        if(state == arrState.length - 1){
            return 0;
        }
        return state + 1;
    }, 0);

    return { apiFunction: arrState[index].fn, mode: modes[index], increment: () => dispatch()}
}

export default useCycle;