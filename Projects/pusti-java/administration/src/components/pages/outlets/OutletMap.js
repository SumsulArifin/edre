import GoogleMap from "components/map/GoogleMap";
import { useEffect, useState } from "react";

const OutletMap = () => {
    const [currentLocation, setCurrentLocation] = useState({ latitude: 0, longitude: 0 });
  
    // Request geolocation permission when the component mounts
    useEffect(() => {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const latitude = position.coords.latitude;
          const longitude = position.coords.longitude;
          if (latitude && longitude) {
            setCurrentLocation({ latitude, longitude });
          }
        },
        (error) => {
          console.error("Error getting the user location:", error);
        }
      );
    }, []);
  
    return (
      <GoogleMap
        initialCenter={{
          lat: currentLocation.latitude,
          lng: currentLocation.longitude,
        }}
        mapStyle="Default"
        className="vh-50 rounded-soft mt-5"
      ></GoogleMap>
    );
  };

  
  export default OutletMap;