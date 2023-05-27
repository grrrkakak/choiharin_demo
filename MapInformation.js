import React, { useEffect, useState } from 'react';
import { Map, MapMarker } from 'react-kakao-maps-sdk';

const NearbyPlaces = () => {
  const [places, setPlaces] = useState([]);
  const [location, setLocation] = useState(null); // 현재 위치를 저장할 상태

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
          `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=100&query=편의점`, // radius : 반경 조절, query = 카테고리
          {
            headers: {
              Authorization: 'KakaoAK 0b34fba760a465783a7d1649a9989945', // 'kakaoAk 본인API키'
            },
          }
        );

        const data = await response.json();
        setPlaces(data.documents); // 받아온 장소 정보를 상태에 저장, place[]에 저장됨
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
	};// 현재 중심 좌표 설정 함수
  

  return (
    <div>
      {location && (
      <Map
        style={{
          width: '500px',
          height: '500px',
        }}
        center={{
          lat: location.latitude, // 기본 지도 중심 위도
          lng: location.longitude // 기본 지도 중심 경도
        }}
      >
        {places.map((place) => (
          <MapMarker
            key={place.id}
            position={{
              lat: place.y, // 장소의 위도
              lng: place.x, // 장소의 경도
            }}>
            <div style={{color:"#000", fontSize: "6px"}}>{place.place_name}</div>
          </MapMarker>
        ))}
      </Map>)}
      <ul>
      {places.map((place) => (
        <li key={place.id}>{place.place_name}</li>
      ))}
    </ul>
    </div>
  );
};

export default NearbyPlaces;