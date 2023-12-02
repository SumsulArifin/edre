import { useEffect, useState } from 'react';
import styles from './style.module.css';
import axios from 'axios';
import { authHeader } from 'utils';
const ToggleButton = ({ setCount, url, status }) => {

    const [itemStatus, setItemStatus] = useState(status);
    const [isLoading,setIsLoading] = useState(false);

    // Update Enable_Disable
    useEffect(() => {
        const updateItemStatusData = {
            status: itemStatus
        }

        setIsLoading(true);

        axios.put(url, updateItemStatusData, { headers: authHeader() })
            .then(response => {
                if (response.data) {
                    setIsLoading(false)
                    setCount(1);
                }

            })
            .catch(error => {
                setIsLoading(false)
                console.log(error)
            })

    }, [itemStatus])

    if(isLoading){
        return <>....</>
    }
    return (<>

        <label className={styles.switch}>
            <input type="checkbox" checked={itemStatus === true} onChange={() => setItemStatus(!itemStatus)} />
            <span className={`${styles.slider} ${styles.round}`}></span>
        </label>
    </>
    )
}

export default ToggleButton;