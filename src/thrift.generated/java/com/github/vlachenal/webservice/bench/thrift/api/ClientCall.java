/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.vlachenal.webservice.bench.thrift.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class ClientCall implements org.apache.thrift.TBase<ClientCall, ClientCall._Fields>, java.io.Serializable, Cloneable, Comparable<ClientCall> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ClientCall");

  private static final org.apache.thrift.protocol.TField REQUEST_SEQ_FIELD_DESC = new org.apache.thrift.protocol.TField("requestSeq", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PROTOCOL_FIELD_DESC = new org.apache.thrift.protocol.TField("protocol", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField METHOD_FIELD_DESC = new org.apache.thrift.protocol.TField("method", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField CLIENT_START_FIELD_DESC = new org.apache.thrift.protocol.TField("clientStart", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField CLIENT_END_FIELD_DESC = new org.apache.thrift.protocol.TField("clientEnd", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField OK_FIELD_DESC = new org.apache.thrift.protocol.TField("ok", org.apache.thrift.protocol.TType.BOOL, (short)6);
  private static final org.apache.thrift.protocol.TField ERR_MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("errMsg", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ClientCallStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ClientCallTupleSchemeFactory();

  private int requestSeq; // required
  private java.lang.String protocol; // required
  private java.lang.String method; // required
  private long clientStart; // required
  private long clientEnd; // required
  private boolean ok; // required
  private java.lang.String errMsg; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * Request sequence identifier
     */
    REQUEST_SEQ((short)1, "requestSeq"),
    /**
     * Protocol (always 'thrift')
     */
    PROTOCOL((short)2, "protocol"),
    /**
     * The method which has been called
     */
    METHOD((short)3, "method"),
    /**
     * Client start timestamp
     */
    CLIENT_START((short)4, "clientStart"),
    /**
     * Client end timestamp
     */
    CLIENT_END((short)5, "clientEnd"),
    /**
     * Call status
     */
    OK((short)6, "ok"),
    /**
     * Error message
     */
    ERR_MSG((short)7, "errMsg");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REQUEST_SEQ
          return REQUEST_SEQ;
        case 2: // PROTOCOL
          return PROTOCOL;
        case 3: // METHOD
          return METHOD;
        case 4: // CLIENT_START
          return CLIENT_START;
        case 5: // CLIENT_END
          return CLIENT_END;
        case 6: // OK
          return OK;
        case 7: // ERR_MSG
          return ERR_MSG;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __REQUESTSEQ_ISSET_ID = 0;
  private static final int __CLIENTSTART_ISSET_ID = 1;
  private static final int __CLIENTEND_ISSET_ID = 2;
  private static final int __OK_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REQUEST_SEQ, new org.apache.thrift.meta_data.FieldMetaData("requestSeq", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PROTOCOL, new org.apache.thrift.meta_data.FieldMetaData("protocol", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.METHOD, new org.apache.thrift.meta_data.FieldMetaData("method", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLIENT_START, new org.apache.thrift.meta_data.FieldMetaData("clientStart", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CLIENT_END, new org.apache.thrift.meta_data.FieldMetaData("clientEnd", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.OK, new org.apache.thrift.meta_data.FieldMetaData("ok", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ERR_MSG, new org.apache.thrift.meta_data.FieldMetaData("errMsg", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ClientCall.class, metaDataMap);
  }

  public ClientCall() {
    this.protocol = "thrift";

    this.ok = false;

  }

  public ClientCall(
    int requestSeq,
    java.lang.String protocol,
    java.lang.String method,
    long clientStart,
    long clientEnd,
    boolean ok,
    java.lang.String errMsg)
  {
    this();
    this.requestSeq = requestSeq;
    setRequestSeqIsSet(true);
    this.protocol = protocol;
    this.method = method;
    this.clientStart = clientStart;
    setClientStartIsSet(true);
    this.clientEnd = clientEnd;
    setClientEndIsSet(true);
    this.ok = ok;
    setOkIsSet(true);
    this.errMsg = errMsg;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ClientCall(ClientCall other) {
    __isset_bitfield = other.__isset_bitfield;
    this.requestSeq = other.requestSeq;
    if (other.isSetProtocol()) {
      this.protocol = other.protocol;
    }
    if (other.isSetMethod()) {
      this.method = other.method;
    }
    this.clientStart = other.clientStart;
    this.clientEnd = other.clientEnd;
    this.ok = other.ok;
    if (other.isSetErrMsg()) {
      this.errMsg = other.errMsg;
    }
  }

  public ClientCall deepCopy() {
    return new ClientCall(this);
  }

  @Override
  public void clear() {
    setRequestSeqIsSet(false);
    this.requestSeq = 0;
    this.protocol = "thrift";

    this.method = null;
    setClientStartIsSet(false);
    this.clientStart = 0;
    setClientEndIsSet(false);
    this.clientEnd = 0;
    this.ok = false;

    this.errMsg = null;
  }

  /**
   * Request sequence identifier
   */
  public int getRequestSeq() {
    return this.requestSeq;
  }

  /**
   * Request sequence identifier
   */
  public void setRequestSeq(int requestSeq) {
    this.requestSeq = requestSeq;
    setRequestSeqIsSet(true);
  }

  public void unsetRequestSeq() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __REQUESTSEQ_ISSET_ID);
  }

  /** Returns true if field requestSeq is set (has been assigned a value) and false otherwise */
  public boolean isSetRequestSeq() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __REQUESTSEQ_ISSET_ID);
  }

  public void setRequestSeqIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __REQUESTSEQ_ISSET_ID, value);
  }

  /**
   * Protocol (always 'thrift')
   */
  public java.lang.String getProtocol() {
    return this.protocol;
  }

  /**
   * Protocol (always 'thrift')
   */
  public void setProtocol(java.lang.String protocol) {
    this.protocol = protocol;
  }

  public void unsetProtocol() {
    this.protocol = null;
  }

  /** Returns true if field protocol is set (has been assigned a value) and false otherwise */
  public boolean isSetProtocol() {
    return this.protocol != null;
  }

  public void setProtocolIsSet(boolean value) {
    if (!value) {
      this.protocol = null;
    }
  }

  /**
   * The method which has been called
   */
  public java.lang.String getMethod() {
    return this.method;
  }

  /**
   * The method which has been called
   */
  public void setMethod(java.lang.String method) {
    this.method = method;
  }

  public void unsetMethod() {
    this.method = null;
  }

  /** Returns true if field method is set (has been assigned a value) and false otherwise */
  public boolean isSetMethod() {
    return this.method != null;
  }

  public void setMethodIsSet(boolean value) {
    if (!value) {
      this.method = null;
    }
  }

  /**
   * Client start timestamp
   */
  public long getClientStart() {
    return this.clientStart;
  }

  /**
   * Client start timestamp
   */
  public void setClientStart(long clientStart) {
    this.clientStart = clientStart;
    setClientStartIsSet(true);
  }

  public void unsetClientStart() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CLIENTSTART_ISSET_ID);
  }

  /** Returns true if field clientStart is set (has been assigned a value) and false otherwise */
  public boolean isSetClientStart() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CLIENTSTART_ISSET_ID);
  }

  public void setClientStartIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CLIENTSTART_ISSET_ID, value);
  }

  /**
   * Client end timestamp
   */
  public long getClientEnd() {
    return this.clientEnd;
  }

  /**
   * Client end timestamp
   */
  public void setClientEnd(long clientEnd) {
    this.clientEnd = clientEnd;
    setClientEndIsSet(true);
  }

  public void unsetClientEnd() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CLIENTEND_ISSET_ID);
  }

  /** Returns true if field clientEnd is set (has been assigned a value) and false otherwise */
  public boolean isSetClientEnd() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CLIENTEND_ISSET_ID);
  }

  public void setClientEndIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CLIENTEND_ISSET_ID, value);
  }

  /**
   * Call status
   */
  public boolean isOk() {
    return this.ok;
  }

  /**
   * Call status
   */
  public void setOk(boolean ok) {
    this.ok = ok;
    setOkIsSet(true);
  }

  public void unsetOk() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __OK_ISSET_ID);
  }

  /** Returns true if field ok is set (has been assigned a value) and false otherwise */
  public boolean isSetOk() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __OK_ISSET_ID);
  }

  public void setOkIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __OK_ISSET_ID, value);
  }

  /**
   * Error message
   */
  public java.lang.String getErrMsg() {
    return this.errMsg;
  }

  /**
   * Error message
   */
  public void setErrMsg(java.lang.String errMsg) {
    this.errMsg = errMsg;
  }

  public void unsetErrMsg() {
    this.errMsg = null;
  }

  /** Returns true if field errMsg is set (has been assigned a value) and false otherwise */
  public boolean isSetErrMsg() {
    return this.errMsg != null;
  }

  public void setErrMsgIsSet(boolean value) {
    if (!value) {
      this.errMsg = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case REQUEST_SEQ:
      if (value == null) {
        unsetRequestSeq();
      } else {
        setRequestSeq((java.lang.Integer)value);
      }
      break;

    case PROTOCOL:
      if (value == null) {
        unsetProtocol();
      } else {
        setProtocol((java.lang.String)value);
      }
      break;

    case METHOD:
      if (value == null) {
        unsetMethod();
      } else {
        setMethod((java.lang.String)value);
      }
      break;

    case CLIENT_START:
      if (value == null) {
        unsetClientStart();
      } else {
        setClientStart((java.lang.Long)value);
      }
      break;

    case CLIENT_END:
      if (value == null) {
        unsetClientEnd();
      } else {
        setClientEnd((java.lang.Long)value);
      }
      break;

    case OK:
      if (value == null) {
        unsetOk();
      } else {
        setOk((java.lang.Boolean)value);
      }
      break;

    case ERR_MSG:
      if (value == null) {
        unsetErrMsg();
      } else {
        setErrMsg((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case REQUEST_SEQ:
      return getRequestSeq();

    case PROTOCOL:
      return getProtocol();

    case METHOD:
      return getMethod();

    case CLIENT_START:
      return getClientStart();

    case CLIENT_END:
      return getClientEnd();

    case OK:
      return isOk();

    case ERR_MSG:
      return getErrMsg();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case REQUEST_SEQ:
      return isSetRequestSeq();
    case PROTOCOL:
      return isSetProtocol();
    case METHOD:
      return isSetMethod();
    case CLIENT_START:
      return isSetClientStart();
    case CLIENT_END:
      return isSetClientEnd();
    case OK:
      return isSetOk();
    case ERR_MSG:
      return isSetErrMsg();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ClientCall)
      return this.equals((ClientCall)that);
    return false;
  }

  public boolean equals(ClientCall that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_requestSeq = true;
    boolean that_present_requestSeq = true;
    if (this_present_requestSeq || that_present_requestSeq) {
      if (!(this_present_requestSeq && that_present_requestSeq))
        return false;
      if (this.requestSeq != that.requestSeq)
        return false;
    }

    boolean this_present_protocol = true && this.isSetProtocol();
    boolean that_present_protocol = true && that.isSetProtocol();
    if (this_present_protocol || that_present_protocol) {
      if (!(this_present_protocol && that_present_protocol))
        return false;
      if (!this.protocol.equals(that.protocol))
        return false;
    }

    boolean this_present_method = true && this.isSetMethod();
    boolean that_present_method = true && that.isSetMethod();
    if (this_present_method || that_present_method) {
      if (!(this_present_method && that_present_method))
        return false;
      if (!this.method.equals(that.method))
        return false;
    }

    boolean this_present_clientStart = true;
    boolean that_present_clientStart = true;
    if (this_present_clientStart || that_present_clientStart) {
      if (!(this_present_clientStart && that_present_clientStart))
        return false;
      if (this.clientStart != that.clientStart)
        return false;
    }

    boolean this_present_clientEnd = true;
    boolean that_present_clientEnd = true;
    if (this_present_clientEnd || that_present_clientEnd) {
      if (!(this_present_clientEnd && that_present_clientEnd))
        return false;
      if (this.clientEnd != that.clientEnd)
        return false;
    }

    boolean this_present_ok = true;
    boolean that_present_ok = true;
    if (this_present_ok || that_present_ok) {
      if (!(this_present_ok && that_present_ok))
        return false;
      if (this.ok != that.ok)
        return false;
    }

    boolean this_present_errMsg = true && this.isSetErrMsg();
    boolean that_present_errMsg = true && that.isSetErrMsg();
    if (this_present_errMsg || that_present_errMsg) {
      if (!(this_present_errMsg && that_present_errMsg))
        return false;
      if (!this.errMsg.equals(that.errMsg))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + requestSeq;

    hashCode = hashCode * 8191 + ((isSetProtocol()) ? 131071 : 524287);
    if (isSetProtocol())
      hashCode = hashCode * 8191 + protocol.hashCode();

    hashCode = hashCode * 8191 + ((isSetMethod()) ? 131071 : 524287);
    if (isSetMethod())
      hashCode = hashCode * 8191 + method.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(clientStart);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(clientEnd);

    hashCode = hashCode * 8191 + ((ok) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetErrMsg()) ? 131071 : 524287);
    if (isSetErrMsg())
      hashCode = hashCode * 8191 + errMsg.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ClientCall other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRequestSeq()).compareTo(other.isSetRequestSeq());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRequestSeq()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.requestSeq, other.requestSeq);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetProtocol()).compareTo(other.isSetProtocol());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProtocol()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.protocol, other.protocol);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMethod()).compareTo(other.isSetMethod());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMethod()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.method, other.method);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetClientStart()).compareTo(other.isSetClientStart());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClientStart()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.clientStart, other.clientStart);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetClientEnd()).compareTo(other.isSetClientEnd());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClientEnd()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.clientEnd, other.clientEnd);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOk()).compareTo(other.isSetOk());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOk()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ok, other.ok);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetErrMsg()).compareTo(other.isSetErrMsg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrMsg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errMsg, other.errMsg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ClientCall(");
    boolean first = true;

    sb.append("requestSeq:");
    sb.append(this.requestSeq);
    first = false;
    if (!first) sb.append(", ");
    sb.append("protocol:");
    if (this.protocol == null) {
      sb.append("null");
    } else {
      sb.append(this.protocol);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("method:");
    if (this.method == null) {
      sb.append("null");
    } else {
      sb.append(this.method);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("clientStart:");
    sb.append(this.clientStart);
    first = false;
    if (!first) sb.append(", ");
    sb.append("clientEnd:");
    sb.append(this.clientEnd);
    first = false;
    if (!first) sb.append(", ");
    sb.append("ok:");
    sb.append(this.ok);
    first = false;
    if (!first) sb.append(", ");
    sb.append("errMsg:");
    if (this.errMsg == null) {
      sb.append("null");
    } else {
      sb.append(this.errMsg);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetRequestSeq()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'requestSeq' is unset! Struct:" + toString());
    }

    if (!isSetMethod()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'method' is unset! Struct:" + toString());
    }

    if (!isSetClientStart()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'clientStart' is unset! Struct:" + toString());
    }

    if (!isSetClientEnd()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'clientEnd' is unset! Struct:" + toString());
    }

    if (!isSetOk()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'ok' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ClientCallStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ClientCallStandardScheme getScheme() {
      return new ClientCallStandardScheme();
    }
  }

  private static class ClientCallStandardScheme extends org.apache.thrift.scheme.StandardScheme<ClientCall> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ClientCall struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REQUEST_SEQ
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.requestSeq = iprot.readI32();
              struct.setRequestSeqIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROTOCOL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.protocol = iprot.readString();
              struct.setProtocolIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // METHOD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.method = iprot.readString();
              struct.setMethodIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CLIENT_START
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.clientStart = iprot.readI64();
              struct.setClientStartIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CLIENT_END
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.clientEnd = iprot.readI64();
              struct.setClientEndIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // OK
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.ok = iprot.readBool();
              struct.setOkIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // ERR_MSG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.errMsg = iprot.readString();
              struct.setErrMsgIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ClientCall struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(REQUEST_SEQ_FIELD_DESC);
      oprot.writeI32(struct.requestSeq);
      oprot.writeFieldEnd();
      if (struct.protocol != null) {
        oprot.writeFieldBegin(PROTOCOL_FIELD_DESC);
        oprot.writeString(struct.protocol);
        oprot.writeFieldEnd();
      }
      if (struct.method != null) {
        oprot.writeFieldBegin(METHOD_FIELD_DESC);
        oprot.writeString(struct.method);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CLIENT_START_FIELD_DESC);
      oprot.writeI64(struct.clientStart);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CLIENT_END_FIELD_DESC);
      oprot.writeI64(struct.clientEnd);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(OK_FIELD_DESC);
      oprot.writeBool(struct.ok);
      oprot.writeFieldEnd();
      if (struct.errMsg != null) {
        oprot.writeFieldBegin(ERR_MSG_FIELD_DESC);
        oprot.writeString(struct.errMsg);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ClientCallTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ClientCallTupleScheme getScheme() {
      return new ClientCallTupleScheme();
    }
  }

  private static class ClientCallTupleScheme extends org.apache.thrift.scheme.TupleScheme<ClientCall> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ClientCall struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI32(struct.requestSeq);
      oprot.writeString(struct.method);
      oprot.writeI64(struct.clientStart);
      oprot.writeI64(struct.clientEnd);
      oprot.writeBool(struct.ok);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetProtocol()) {
        optionals.set(0);
      }
      if (struct.isSetErrMsg()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetProtocol()) {
        oprot.writeString(struct.protocol);
      }
      if (struct.isSetErrMsg()) {
        oprot.writeString(struct.errMsg);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ClientCall struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.requestSeq = iprot.readI32();
      struct.setRequestSeqIsSet(true);
      struct.method = iprot.readString();
      struct.setMethodIsSet(true);
      struct.clientStart = iprot.readI64();
      struct.setClientStartIsSet(true);
      struct.clientEnd = iprot.readI64();
      struct.setClientEndIsSet(true);
      struct.ok = iprot.readBool();
      struct.setOkIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.protocol = iprot.readString();
        struct.setProtocolIsSet(true);
      }
      if (incoming.get(1)) {
        struct.errMsg = iprot.readString();
        struct.setErrMsgIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

