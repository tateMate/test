import './App.css';
import React, { useState } from 'react';
import Login from './Login';
import Result from './Result';
import { Route, Routes } from 'react-router-dom';
import UserContext from './store/user-context';
function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/result' element={<Result />} />
      </Routes>
    </>
  );
}

export default App;
