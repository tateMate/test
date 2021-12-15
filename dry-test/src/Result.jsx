import { useEffect, useContext } from 'react';
import axios from 'axios';
import UserContext from './store/user-context';

const Result = () => {
  const ctx = useContext(UserContext);
  // const fetchUserInput = async () => {
  //   axios.post('http://localhost:3000/result', /);
  // };
  return <div>{ctx.userInput}</div>;
};

export default Result;
