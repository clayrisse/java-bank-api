package com.project.bankapi.model.mOperation;

import com.project.bankapi.enums.OpType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "opId")
public class OutgoingOp extends Operation {

    public OutgoingOp() { super(OpType.OUTGOING); }

}
