import React, { useEffect, useState, useRef } from 'react';
import { Map, MapMarker } from 'react-kakao-maps-sdk';

const NearbyPlaces = () => {
  const [places, setPlaces] = useState([]);
  const MyLat = useRef() // 현재 위치 정보를 레퍼런스로 전달하기 위해 설정한 것
  const MyLng = useRef()

  useEffect(() => {
    const fetchNearbyPlaces = async () => {
      try {
        // 현재 위치 정보 가져오기
        const position = await new Promise((resolve, reject) => {
          navigator.geolocation.getCurrentPosition(resolve, reject);
        });

        const curLatitude = position.coords.latitude;
        const curLongitude = position.coords.longitude;
        MyLat.current = curLatitude
        MyLng.current = curLongitude

        // Kakao Map API로부터 주변 장소 정보를 받아오기, cur..변수는 위 함수에서 받아온 좌표 radius는 반경 설정, query는 카테고리
        const response = await fetch(
          `https://dapi.kakao.com/v2/local/search/keyword.json?x=${curLongitude}&y=${curLatitude}&radius=100&query=편의점`, 
          {
            headers: {
              Authorization: 'KakaoAK 0b34fba760a465783a7d1649a9989945', // 본인의 Kakao Map API 키로 변경합니다.
            },
          }
        );

        const data = await response.json();
        setPlaces(data.documents); // 받아온 장소 정보 저장, places[]에 저장
      } catch (error) {
        console.error(error);
      }
    };

    fetchNearbyPlaces();
  }, []);

  return (
    <div>
      <Map
        style={{
          width: '500px',
          height: '500px',
        }}
        center={{
          lat: 37.5139, // 기본 지도 중심 위도
          lng: 127.0375, // 기본 지도 중심 경도
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
          </MapMarker> // 마커와 인포윈도우 출력, 인포윈도우는 가게이름만
        ))}
      </Map>
      <ul> 
      {places.map((place) => (
        <li key={place.id}>{place.place_name}</li>
      ))}
    </ul> 
    </div>// 가게 이름 화면에 출력용
  );
};

export default NearbyPlaces;