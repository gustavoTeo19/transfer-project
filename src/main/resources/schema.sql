CREATE TABLE tb_transfer_fee_schedule (
    id UUID PRIMARY KEY,
    start_days INT NOT NULL,
    end_days INT NOT NULL,
    fixed_fee DECIMAL(19,2) NOT NULL,
    percentual_fee DECIMAL(19,4) NOT NULL
);

CREATE TABLE TB_TRANSFER (
    id UUID PRIMARY KEY,
    source_account VARCHAR(255) NOT NULL,
    destination_account VARCHAR(255) NOT NULL,
    transfer_amount DECIMAL(19,2) NOT NULL,
    applied_fee DECIMAL(19,2) NOT NULL,
    transfer_date TIMESTAMP NOT NULL,
    scheduling_date TIMESTAMP NOT NULL,
    fee_schedule_id UUID NOT NULL,
    FOREIGN KEY (fee_schedule_id) REFERENCES tb_transfer_fee_schedule(id)
);