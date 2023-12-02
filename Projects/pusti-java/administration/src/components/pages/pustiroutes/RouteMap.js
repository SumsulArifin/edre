import GoogleMap from "components/map/GoogleMap";
import { useEffect, useState } from "react";

const RouteMap = () => {
    const [currentLocation, setCurrentLocation] = useState({ latitude: 0, longitude: 0 });


    // Get Current Latitude & Longitude.
    const successCallBack = (position) => {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;
        latitude && longitude && setCurrentLocation({ latitude: latitude, longitude: longitude });
    }

    // Handle Error
    const errorCallBack = (error) => {
        console.error('Error getting the user location:', error);
    }
    useEffect(() => {
        // Get User Current Location
        const watchId = navigator.geolocation.watchPosition(successCallBack, errorCallBack);

        return () => {
            // Stop watching the user's location when the component unmounts
            navigator.geolocation.clearWatch(watchId);
        }

    }, [])


    return <GoogleMap
        initialCenter={{
            lat: currentLocation?.latitude,
            lng: currentLocation?.longitude
        }}
        mapStyle="Default"
        className="vh-50 rounded-soft mt-5"
    ></GoogleMap>

}
export default RouteMap;