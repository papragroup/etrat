import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITransactionType, defaultValue } from 'app/shared/model/transaction-type.model';

export const ACTION_TYPES = {
  FETCH_TRANSACTIONTYPE_LIST: 'transactionType/FETCH_TRANSACTIONTYPE_LIST',
  FETCH_TRANSACTIONTYPE: 'transactionType/FETCH_TRANSACTIONTYPE',
  CREATE_TRANSACTIONTYPE: 'transactionType/CREATE_TRANSACTIONTYPE',
  UPDATE_TRANSACTIONTYPE: 'transactionType/UPDATE_TRANSACTIONTYPE',
  DELETE_TRANSACTIONTYPE: 'transactionType/DELETE_TRANSACTIONTYPE',
  RESET: 'transactionType/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITransactionType>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TransactionTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: TransactionTypeState = initialState, action): TransactionTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRANSACTIONTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRANSACTIONTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TRANSACTIONTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_TRANSACTIONTYPE):
    case REQUEST(ACTION_TYPES.DELETE_TRANSACTIONTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TRANSACTIONTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRANSACTIONTYPE):
    case FAILURE(ACTION_TYPES.CREATE_TRANSACTIONTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_TRANSACTIONTYPE):
    case FAILURE(ACTION_TYPES.DELETE_TRANSACTIONTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSACTIONTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSACTIONTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRANSACTIONTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_TRANSACTIONTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRANSACTIONTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/transaction-types';

// Actions

export const getEntities: ICrudGetAllAction<ITransactionType> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRANSACTIONTYPE_LIST,
  payload: axios.get<ITransactionType>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITransactionType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRANSACTIONTYPE,
    payload: axios.get<ITransactionType>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITransactionType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRANSACTIONTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITransactionType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRANSACTIONTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITransactionType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRANSACTIONTYPE,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
