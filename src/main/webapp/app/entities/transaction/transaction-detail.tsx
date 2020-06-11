import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './transaction.reducer';
import { ITransaction } from 'app/shared/model/transaction.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITransactionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TransactionDetail = (props: ITransactionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { transactionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="etratApp.transaction.detail.title">Transaction</Translate> [<b>{transactionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="rrn">
              <Translate contentKey="etratApp.transaction.rrn">Rrn</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.rrn}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="etratApp.transaction.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.amount}</dd>
          <dt>
            <span id="refrence">
              <Translate contentKey="etratApp.transaction.refrence">Refrence</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.refrence}</dd>
          <dt>
            <Translate contentKey="etratApp.transaction.type">Type</Translate>
          </dt>
          <dd>{transactionEntity.type ? transactionEntity.type.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/transaction" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transaction/${transactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ transaction }: IRootState) => ({
  transactionEntity: transaction.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TransactionDetail);
