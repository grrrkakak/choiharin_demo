import React, { useState } from "react";
import "./App.css";
import StoreList from "./StoreList";
import UserCard from "./UserCard";
import ResultCard from "./ResultCard";
import NearbyPlaces from "./MapInformation";

function App() {
  const [selectedPlace, setSelectedPlace] = useState(null);

  const handlePlaceSelect = (place) => {
    setSelectedPlace(place);
  };

  return (
    <div className="App">
      {selectedPlace ? (
        <ResultCard place={selectedPlace} />
      ) : (
        <NearbyPlaces handlePlaceSelect={handlePlaceSelect} />
      )}
    </div>
  );
}

export default App;
