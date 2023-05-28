import React from "react";
// import StoreList from "./StoreList";
import UserCard from "./UserCard";
import NearbyPlaces from "./MapInformation";

// import { useNavigate } from "react-router-dom";

const ResultCard = ({ place, userCards }) => {
  // const navigate = useNavigate();

  // const maxDiscountRate = Math.max(
  //   ...userCards.map((card) => card.discount_rate)
  // );

  // const maxAccumulationRate = Math.max(
  //   ...userCards.map((card) => card.accumulation_rate)
  // );

  const findBestCards = () => {
    let maxDiscountRate = 0;
    let maxAccumulationRate = 0;
    let bestDiscountCard = null;
    let bestAccumulationCard = null;

    if (userCards) {
      userCards.forEach((card) => {
        if (card.benefits) {
          card.benefits.forEach((benefit) => {
            if (benefit.category === place.category_group_code) {
              if (benefit.discount_rate > maxDiscountRate) {
                bestDiscountCard = card.name;
                maxDiscountRate = benefit.discount_rate;
              }

              if (benefit.accumulation_rate > maxAccumulationRate) {
                bestAccumulationCard = card.name;
                maxAccumulationRate = benefit.accumulation_rate;
              }
            }
          });
        }
      });
    }

    // userCards.forEach((card) => {
    //   card.benefits.forEach((benefit) => {
    //     if (benefit.category === place.category_group_code) {
    //       if (benefit.discount_rate > maxDiscountRate) {
    //         bestDiscountCard = card.name;
    //         maxDiscountRate = benefit.discount_rate;
    //       }

    //       if (benefit.accumulation_rate > maxAccumulationRate) {
    //         bestAccumulationCard = card.name;
    //         maxAccumulationRate = benefit.accumulation_rate;
    //       }
    //     }
    //   });
    // });

    return {
      bestDiscountCard,
      bestAccumulationCard,
      maxDiscountRate,
      maxAccumulationRate,
    };
  };

  const {
    bestDiscountCard,
    bestAccumulationCard,
    maxDiscountRate,
    maxAccumulationRate,
  } = findBestCards();

  // const handleGoBack = () => {
  //   navigate.goBack(); // 뒤로 가기
  // };

  return (
    <div>
      <h2>Best Cards for {place.place_name}</h2>
      {bestDiscountCard && (
        <div>
          <h3>Best Discount Card:</h3>
          <p>Card Name: {bestDiscountCard}</p>
          <p>Discount Rate: {maxDiscountRate}</p>
        </div>
      )}
      {bestAccumulationCard && (
        <div>
          <h3>Best Accumulation Card:</h3>
          <p>Card Name: {bestAccumulationCard}</p>
          <p>Accumulation Rate: {maxAccumulationRate}</p>
        </div>
      )}
      {!bestDiscountCard && !bestAccumulationCard && (
        <p>No matching cards found.</p>
      )}
      {/* <button onClick={handleGoBack}>Go Back</button> */}
    </div>
  );
};

export default ResultCard;
