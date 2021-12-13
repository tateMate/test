import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import UserContext from './store/user-context';

const Login = () => {
  const ctx = useContext(UserContext);
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    navigate('/result');
  };

  const handleChange = (e) => {
    ctx.onSetUserInput(e.target.value);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input type='text' onChange={handleChange} />
        <button>전송</button>
      </form>
    </div>
  );
};

export default Login;
