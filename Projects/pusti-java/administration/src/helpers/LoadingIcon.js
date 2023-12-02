import { RotatingLines } from "react-loader-spinner";

const LoadingIcon = () => {
    return <div className="d-flex justify-content-center align-items-center vh-100" >
        <div>
        <RotatingLines
            strokeColor="grey"
            strokeWidth="5"
            animationDuration="0.75"
            width="96"
            visible={true}
        />
        </div>
    </div>
}

export default LoadingIcon;