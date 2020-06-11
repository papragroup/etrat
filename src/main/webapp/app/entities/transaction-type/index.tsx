import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TransactionType from './transaction-type';
import TransactionTypeDetail from './transaction-type-detail';
import TransactionTypeUpdate from './transaction-type-update';
import TransactionTypeDeleteDialog from './transaction-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TransactionTypeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TransactionTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TransactionTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TransactionTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={TransactionType} />
    </Switch>
  </>
);

export default Routes;
