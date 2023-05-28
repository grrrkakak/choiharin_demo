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
  const [location, setLocation] = useState(null); // 현재 위치를 저장할 상태
  const [selectedPlace, setSelectedPlace] = useState(null); // 선택한 가게 정보를 저장할 상태

  useEffect(() => {
    const fetchNearbyPlaces = async () => {
      try {
        // 현재 위치 정보를 가져오기
        const position = await new Promise((resolve, reject) => {
          navigator.geolocation.getCurrentPosition(resolve, reject);
        });

        const curLatitude = position.coords.latitude;
        const curLongitude = position.coords.longitude;

        // Kakao Map API로부터 주변 장소 정보를 요청
        const response = await fetch(
          `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=3000&query=편의점`, // radius : 반경 조절, query = 카테고리
          {
            headers: {
              Authorization: "KakaoAK 6d6a41a56fc2d0c845d327d309d9ba87", // 'kakaoAk 본인API키'
            },
          }
        );

        const data = await response.json();
        setPlaces_1(data.documents); // 받아온 장소 정보를 상태에 저장, place[]에 저장됨
      } catch (error) {
        console.error(error);
      }
    };

    fetchNearbyPlaces();
  }, []);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(successHandler, errorHandler); // 성공시 successHandler, 실패시 errorHandler 함수가 실행된다.
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
    // 선택한 가게의 정보를 반환하거나 다음 단계로 진행
    // 선택한 가게의 정보를 활용하여 다음 단계를 구현
    console.log("Selected place:", place);
    return <ResultCard place={place} />; // 선택된 가게를 ResultCard.js에 props로 보냄
  };

  useEffect(() => {
    const fetchNearbyPlaces = async () => {
      try {
        // 현재 위치 정보를 가져오기
        const position = await new Promise((resolve, reject) => {
          navigator.geolocation.getCurrentPosition(resolve, reject);
        });

        const curLatitude = position.coords.latitude;
        const curLongitude = position.coords.longitude;

        // Kakao Map API로부터 주변 장소 정보를 요청
        const response = await fetch(
          `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=3000&query=음식점`, // radius : 반경 조절, query = 카테고리
          {
            headers: {
              Authorization: "KakaoAK 6d6a41a56fc2d0c845d327d309d9ba87",
              // "KakaoAK 0b34fba760a465783a7d1649a9989945", // 'kakaoAk 본인API키'
            },
          }
        );

        const data = await response.json();
        setPlaces_2(data.documents); // 받아온 장소 정보를 상태에 저장, place[]에 저장됨
      } catch (error) {
        console.error(error);
      }
    };

    fetchNearbyPlaces();
  }, []);
  useEffect(() => {
    const fetchNearbyPlaces = async () => {
      try {
        // 현재 위치 정보를 가져오기
        const position = await new Promise((resolve, reject) => {
          navigator.geolocation.getCurrentPosition(resolve, reject);
        });

        const curLatitude = position.coords.latitude;
        const curLongitude = position.coords.longitude;

        // Kakao Map API로부터 주변 장소 정보를 요청
        const response = await fetch(
          `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=3000&query=마트`, // radius : 반경 조절, query = 카테고리
          {
            headers: {
              Authorization: "KakaoAK 6d6a41a56fc2d0c845d327d309d9ba87",
              // "KakaoAK 0b34fba760a465783a7d1649a9989945", // 'kakaoAk 본인API키'
            },
          }
        );

        const data = await response.json();
        setPlaces_3(data.documents); // 받아온 장소 정보를 상태에 저장, place[]에 저장됨
      } catch (error) {
        console.error(error);
      }
    };

    fetchNearbyPlaces();
  }, []);

  return (
    <div className="BackTemplate" style={{ marginBottom: "0px" }}>
      <div className="App-title">
        <VscCompass></VscCompass> 위치 탐색
      </div>
      {location && (
        <Map
          style={{
            width: "512px",
            height: "300px",
          }}
          center={{
            lat: location.latitude, // 기본 지도 중심 위도
            lng: location.longitude, // 기본 지도 중심 경도
          }}
        >
          {places_1 &&
            places_1.map((place) => (
              <MapMarker
                key={place.id}
                position={{
                  lat: place.y, // 장소의 위도
                  lng: place.x, // 장소의 경도
                }}
                onClick={() => handlePlaceSelect(place)} // 가게를 클릭했을 때 처리할 함수 호출
              >
                <div className="infowindowDesign">{place.place_name}</div>
              </MapMarker>
            ))}
          {places_2 &&
            places_2.map((place) => (
              <MapMarker
                key={place.id}
                position={{
                  lat: place.y, // 장소의 위도
                  lng: place.x, // 장소의 경도
                }}
                onClick={() => handlePlaceSelect(place)} // 가게를 클릭했을 때 처리할 함수 호출
              >
                <div className="infowindowDesign">{place.place_name}</div>
              </MapMarker>
            ))}
          {places_3 &&
            places_3.map((place) => (
              <MapMarker
                key={place.id}
                position={{
                  lat: place.y, // 장소의 위도
                  lng: place.x, // 장소의 경도
                }}
                onClick={() => handlePlaceSelect(place)} // 가게를 클릭했을 때 처리할 함수 호출
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
                <VscLocation></VscLocation> {place.place_name}
              </li>
            ))}
          {places_2 &&
            places_2.map((place) => (
              <li
                key={place.id}
                onClick={() => handlePlaceSelect(place)}
                className="ElementDesign"
              >
                <VscLocation></VscLocation> {place.place_name}
              </li>
            ))}
          {places_3 &&
            places_3.map((place) => (
              <li
                key={place.id}
                onClick={() => handlePlaceSelect(place)}
                className="ElementDesign"
              >
                <VscLocation></VscLocation> {place.place_name}
              </li>
            ))}
        </ul>
      </div>
      {/* 추가 */}
      {/* 선택된 가게 정보를 ResultCard 컴포넌트로 전달 */}
      {selectedPlace && <ResultCard place={selectedPlace} />}
    </div>
  );
};

export default NearbyPlaces;
