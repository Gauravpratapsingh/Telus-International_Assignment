import React, { useState, useEffect } from 'react';
import axios from 'axios';

const RewardCalculator = () => {
  const [transactions, setTransactions] = useState([]);


  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('https://jsonplaceholder.typicode.com/posts');
        const data = response.data.slice(0, 5).map((item, index) => ({
          customerId: index + 1,
          month: 'January',
          amount: Math.floor(Math.random() * 150) + 50 
        }));
        setTransactions(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  
  const calculateRewardPoints = amount => {
    let points = 0;
    if (amount > 100) {
      points += (amount - 100) * 2;
      points += 50; 
    } else if (amount > 50) {
      points += (amount - 50);
    }
    return points;
  };

  
  const calculateRewards = () => {
    const rewards = {};
    transactions.forEach(transaction => {
      const { customerId, month, amount } = transaction;
      const points = calculateRewardPoints(amount);
      if (!rewards[customerId]) {
        rewards[customerId] = { total: 0, months: {} };
      }
      if (!rewards[customerId].months[month]) {
        rewards[customerId].months[month] = 0;
      }
      rewards[customerId].total += points;
      rewards[customerId].months[month] += points;
    });
    return rewards;
  };

  const rewards = calculateRewards();

  return (
    <div>
      <h2>Reward Points Earned</h2>
      {Object.keys(rewards).map(customerId => (
        <div key={customerId}>
          <h3>Customer ID: {customerId}</h3>
          <p>Total Points: {rewards[customerId].total}</p>
          <h4>Points Per Month:</h4>
          <ul>
            {Object.keys(rewards[customerId].months).map(month => (
              <li key={month}>
                {month}: {rewards[customerId].months[month]}
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default RewardCalculator;
