import { ITransactionType } from 'app/shared/model/transaction-type.model';

export interface ITransaction {
  id?: number;
  rrn?: string;
  amount?: number;
  refrence?: number;
  type?: ITransactionType;
}

export const defaultValue: Readonly<ITransaction> = {};
