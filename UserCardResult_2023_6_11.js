import React from "react";
import NearbyPlaces from "./MapComponent_5";
import { Navigate, useNavigate } from "react-router";
import {useRef} from "react"
const UserCardResult = ({place, code}) => {
  const navigate = useNavigate();
  const BestD = useRef()
  const BestA = useRef()
  const MaxD = useRef()
  const MaxA = useRef()

  const userCards = [
    {
      id: 1,
      user_id: 1,
      name: "현대카드M Edition3",
      company: "현대카드",
      benefits: [
        { category: "FD6", discount_rate: 0.1, accumulation_rate: 0.02 },
        { category: "CE7", discount_rate: 0.05, accumulation_rate: 0.03 },
        { category: "CS2", discount_rate: 0.03, accumulation_rate: 0.01 },
      ],
    },
    {
      id: 2,
      user_id: 1,
      name: "카카오 프렌즈 체크카드",
      company: "카카오뱅크",
      benefits: [
        { category: "FD6", discount_rate: 0.05, accumulation_rate: 0.01 },
        { category: "CE7", discount_rate: 0.03, accumulation_rate: 0.02 },
        { category: "CS2", discount_rate: 0.02, accumulation_rate: 0.03 },
      ],
    },
    {
      id: 3,
      user_id: 1,
      name: "첵첵 체크카드",
      company: "KB국민은행",
      benefits: [
        { category: "FD6", discount_rate: 0.03, accumulation_rate: 0.03 },
        { category: "CE7", discount_rate: 0.02, accumulation_rate: 0.02 },
        { category: "CS2", discount_rate: 0.01, accumulation_rate: 0.01 },
      ],
    },
  ];

  const findUserCards = (cards, session_user_id) => {
    return cards.filter((card) => card.user_id === session_user_id);
  };

  const session_user_id = 1; // 로그인 유저의 user_id가 1이라고 가정
  const user_cards_of_user = findUserCards(userCards, session_user_id);

  const findBestCards = () => {
    let maxDiscountRate = 0;
    let maxAccumulationRate = 0;
    let bestDiscountCard = null;
    let bestAccumulationCard = null;

    if (user_cards_of_user) {
      user_cards_of_user.forEach((card) => {
        if (card.benefits) {
          card.benefits.forEach((benefit) => {
            if (benefit.category === code) {
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
    BestD.current=bestDiscountCard
    BestA.current=bestAccumulationCard
    MaxD.current=maxDiscountRate
    MaxA.current=maxAccumulationRate
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

  return (
    <div>
      <h2>Best Cards for {place}</h2>
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
    </div>
  );
};

export default UserCardResult;
