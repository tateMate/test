import React, { useState } from 'react';
const UserContext = React.createContext({
  userInput: '',
});

export const UserContextProvider = (props) => {
  const [userInput, setUserInput] = useState('');

  return (
    <UserContext.Provider
      value={{
        userInput: userInput,
        onSetUserInput: setUserInput,
      }}
    >
      {props.children}
    </UserContext.Provider>
  );
};

export default UserContext;
