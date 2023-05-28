import React, { useEffect, useState } from "react";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import "./Template.scss";
import "./ListTemplate.scss";
import { VscCompass, VscLocation } from "react-icons/vsc";
import ResultCard from "./ResultCard";

const NearbyPlaces = () => {
  const [places_1, setPlaces_1] = useState([]);
  const [places_2, setPlaces_2] = useState([]);
  const [places_3, setPlaces_3] = useState([]);
  const [location, setLocation] = useState(null);
  const [selectedPlace, setSelectedPlace] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const position = await new Promise((resolve, reject) => {
          navigator.geolocation.getCurrentPosition(resolve, reject);
        });

        const curLatitude = position.coords.latitude;
        const curLongitude = position.coords.longitude;

        const requests = [
          fetch(
            `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=3000&query=편의점`,
            {
              headers: {
                Authorization: "KakaoAK 08f7a05aadfcba0868ec3ac3bd1da8ea",
              },
            }
          ),
          fetch(
            `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=3000&query=음식점`,
            {
              headers: {
                Authorization: "KakaoAK 08f7a05aadfcba0868ec3ac3bd1da8ea",
              },
            }
          ),
          fetch(
            `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=3000&query=마트`,
            {
              headers: {
                Authorization: "KakaoAK 08f7a05aadfcba0868ec3ac3bd1da8ea",
              },
            }
          ),
        ];

        const responses = await Promise.all(requests);
        const data = await Promise.all(
          responses.map((response) => response.json())
        );
        setPlaces_1(data[0].documents);
        setPlaces_2(data[1].documents);
        setPlaces_3(data[2].documents);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(successHandler, errorHandler);
  }, []);

  const successHandler = (response) => {
    const { latitude, longitude } = response.coords;
    setLocation({ latitude, longitude });
  };

  const errorHandler = (error) => {
    console.log(error);
  };

  const handlePlaceSelect = (place) => {
    setSelectedPlace(place);
    console.log("Selected place:", place);
  };

  return (
    <div className="BackTemplate" style={{ marginBottom: "0px" }}>
      <div className="App-title">
        <VscCompass /> 위치 탐색
      </div>
      {location && (
        <Map
          style={{
            width: "512px",
            height: "300px",
          }}
          center={{
            lat: location.latitude,
            lng: location.longitude,
          }}
        >
          {places_1 &&
            places_1.map((place) => (
              <MapMarker
                key={place.id}
                position={{
                  lat: place.y,
                  lng: place.x,
                }}
                onClick={() => handlePlaceSelect(place)}
              >
                <div className="infowindowDesign">{place.place_name}</div>
              </MapMarker>
            ))}
          {places_2 &&
            places_2.map((place) => (
              <MapMarker
                key={place.id}
                position={{
                  lat: place.y,
                  lng: place.x,
                }}
                onClick={() => handlePlaceSelect(place)}
              >
                <div className="infowindowDesign">{place.place_name}</div>
              </MapMarker>
            ))}
          {places_3 &&
            places_3.map((place) => (
              <MapMarker
                key={place.id}
                position={{
                  lat: place.y,
                  lng: place.x,
                }}
                onClick={() => handlePlaceSelect(place)}
              >
                <div className="infowindowDesign">{place.place_name}</div>
              </MapMarker>
            ))}
        </Map>
      )}
      <div className="BackTemplate" style={{ marginTop: "0px" }}>
        <div
          className="App-title"
          style={{ fontSize: "1rem", height: "1.5rem", marginBottom: "0px" }}
        >
          장소 선택
        </div>
        <ul className="ListDesign">
          {places_1 &&
            places_1.map((place) => (
              <li
                key={place.id}
                onClick={() => handlePlaceSelect(place)}
                className="ElementDesign"
              >
                <VscLocation /> {place.place_name}
              </li>
            ))}
          {places_2 &&
            places_2.map((place) => (
              <li
                key={place.id}
                onClick={() => handlePlaceSelect(place)}
                className="ElementDesign"
              >
                <VscLocation /> {place.place_name}
              </li>
            ))}
          {places_3 &&
            places_3.map((place) => (
              <li
                key={place.id}
                onClick={() => handlePlaceSelect(place)}
                className="ElementDesign"
              >
                <VscLocation /> {place.place_name}
              </li>
            ))}
        </ul>
      </div>
      {selectedPlace && <ResultCard place={selectedPlace} />}
    </div>
  );
};

export default NearbyPlaces;
