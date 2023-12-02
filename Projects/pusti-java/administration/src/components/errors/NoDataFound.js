import notFoundImg from "assets/img/gallery/no-data (1).png"
const NoDataFound = () => {
    return <div className="text-center p-3 ">
        <img src={notFoundImg}></img>
        No data found</div>
}

export default NoDataFound;