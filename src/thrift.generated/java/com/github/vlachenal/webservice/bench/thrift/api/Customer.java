/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.vlachenal.webservice.bench.thrift.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * Customer
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)")
public class Customer implements org.apache.thrift.TBase<Customer, Customer._Fields>, java.io.Serializable, Cloneable, Comparable<Customer> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Customer");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField FIRST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("firstName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField LAST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("lastName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField BIRTH_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("birthDate", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField EMAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("email", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField ADDRESS_FIELD_DESC = new org.apache.thrift.protocol.TField("address", org.apache.thrift.protocol.TType.STRUCT, (short)6);
  private static final org.apache.thrift.protocol.TField PHONES_FIELD_DESC = new org.apache.thrift.protocol.TField("phones", org.apache.thrift.protocol.TType.LIST, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new CustomerStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new CustomerTupleSchemeFactory();

  private @org.apache.thrift.annotation.Nullable java.lang.String id; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String firstName; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String lastName; // required
  private long birthDate; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String email; // required
  private @org.apache.thrift.annotation.Nullable Address address; // required
  private @org.apache.thrift.annotation.Nullable java.util.List<Phone> phones; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * Identifier
     */
    ID((short)1, "id"),
    /**
     * First name
     */
    FIRST_NAME((short)2, "firstName"),
    /**
     * Last name
     */
    LAST_NAME((short)3, "lastName"),
    /**
     * Brith date
     */
    BIRTH_DATE((short)4, "birthDate"),
    /**
     * Email address
     */
    EMAIL((short)5, "email"),
    /**
     * Address
     */
    ADDRESS((short)6, "address"),
    /**
     * Phones
     */
    PHONES((short)7, "phones");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // FIRST_NAME
          return FIRST_NAME;
        case 3: // LAST_NAME
          return LAST_NAME;
        case 4: // BIRTH_DATE
          return BIRTH_DATE;
        case 5: // EMAIL
          return EMAIL;
        case 6: // ADDRESS
          return ADDRESS;
        case 7: // PHONES
          return PHONES;
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
    @org.apache.thrift.annotation.Nullable
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
  private static final int __BIRTHDATE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FIRST_NAME, new org.apache.thrift.meta_data.FieldMetaData("firstName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LAST_NAME, new org.apache.thrift.meta_data.FieldMetaData("lastName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BIRTH_DATE, new org.apache.thrift.meta_data.FieldMetaData("birthDate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EMAIL, new org.apache.thrift.meta_data.FieldMetaData("email", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ADDRESS, new org.apache.thrift.meta_data.FieldMetaData("address", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Address.class)));
    tmpMap.put(_Fields.PHONES, new org.apache.thrift.meta_data.FieldMetaData("phones", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Phone.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Customer.class, metaDataMap);
  }

  public Customer() {
  }

  public Customer(
    java.lang.String id,
    java.lang.String firstName,
    java.lang.String lastName,
    long birthDate,
    java.lang.String email,
    Address address,
    java.util.List<Phone> phones)
  {
    this();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    setBirthDateIsSet(true);
    this.email = email;
    this.address = address;
    this.phones = phones;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Customer(Customer other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetFirstName()) {
      this.firstName = other.firstName;
    }
    if (other.isSetLastName()) {
      this.lastName = other.lastName;
    }
    this.birthDate = other.birthDate;
    if (other.isSetEmail()) {
      this.email = other.email;
    }
    if (other.isSetAddress()) {
      this.address = new Address(other.address);
    }
    if (other.isSetPhones()) {
      java.util.List<Phone> __this__phones = new java.util.ArrayList<Phone>(other.phones.size());
      for (Phone other_element : other.phones) {
        __this__phones.add(new Phone(other_element));
      }
      this.phones = __this__phones;
    }
  }

  public Customer deepCopy() {
    return new Customer(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.firstName = null;
    this.lastName = null;
    setBirthDateIsSet(false);
    this.birthDate = 0;
    this.email = null;
    this.address = null;
    this.phones = null;
  }

  /**
   * Identifier
   */
  @org.apache.thrift.annotation.Nullable
  public java.lang.String getId() {
    return this.id;
  }

  /**
   * Identifier
   */
  public void setId(@org.apache.thrift.annotation.Nullable java.lang.String id) {
    this.id = id;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  /**
   * First name
   */
  @org.apache.thrift.annotation.Nullable
  public java.lang.String getFirstName() {
    return this.firstName;
  }

  /**
   * First name
   */
  public void setFirstName(@org.apache.thrift.annotation.Nullable java.lang.String firstName) {
    this.firstName = firstName;
  }

  public void unsetFirstName() {
    this.firstName = null;
  }

  /** Returns true if field firstName is set (has been assigned a value) and false otherwise */
  public boolean isSetFirstName() {
    return this.firstName != null;
  }

  public void setFirstNameIsSet(boolean value) {
    if (!value) {
      this.firstName = null;
    }
  }

  /**
   * Last name
   */
  @org.apache.thrift.annotation.Nullable
  public java.lang.String getLastName() {
    return this.lastName;
  }

  /**
   * Last name
   */
  public void setLastName(@org.apache.thrift.annotation.Nullable java.lang.String lastName) {
    this.lastName = lastName;
  }

  public void unsetLastName() {
    this.lastName = null;
  }

  /** Returns true if field lastName is set (has been assigned a value) and false otherwise */
  public boolean isSetLastName() {
    return this.lastName != null;
  }

  public void setLastNameIsSet(boolean value) {
    if (!value) {
      this.lastName = null;
    }
  }

  /**
   * Brith date
   */
  public long getBirthDate() {
    return this.birthDate;
  }

  /**
   * Brith date
   */
  public void setBirthDate(long birthDate) {
    this.birthDate = birthDate;
    setBirthDateIsSet(true);
  }

  public void unsetBirthDate() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __BIRTHDATE_ISSET_ID);
  }

  /** Returns true if field birthDate is set (has been assigned a value) and false otherwise */
  public boolean isSetBirthDate() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __BIRTHDATE_ISSET_ID);
  }

  public void setBirthDateIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __BIRTHDATE_ISSET_ID, value);
  }

  /**
   * Email address
   */
  @org.apache.thrift.annotation.Nullable
  public java.lang.String getEmail() {
    return this.email;
  }

  /**
   * Email address
   */
  public void setEmail(@org.apache.thrift.annotation.Nullable java.lang.String email) {
    this.email = email;
  }

  public void unsetEmail() {
    this.email = null;
  }

  /** Returns true if field email is set (has been assigned a value) and false otherwise */
  public boolean isSetEmail() {
    return this.email != null;
  }

  public void setEmailIsSet(boolean value) {
    if (!value) {
      this.email = null;
    }
  }

  /**
   * Address
   */
  @org.apache.thrift.annotation.Nullable
  public Address getAddress() {
    return this.address;
  }

  /**
   * Address
   */
  public void setAddress(@org.apache.thrift.annotation.Nullable Address address) {
    this.address = address;
  }

  public void unsetAddress() {
    this.address = null;
  }

  /** Returns true if field address is set (has been assigned a value) and false otherwise */
  public boolean isSetAddress() {
    return this.address != null;
  }

  public void setAddressIsSet(boolean value) {
    if (!value) {
      this.address = null;
    }
  }

  public int getPhonesSize() {
    return (this.phones == null) ? 0 : this.phones.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<Phone> getPhonesIterator() {
    return (this.phones == null) ? null : this.phones.iterator();
  }

  public void addToPhones(Phone elem) {
    if (this.phones == null) {
      this.phones = new java.util.ArrayList<Phone>();
    }
    this.phones.add(elem);
  }

  /**
   * Phones
   */
  @org.apache.thrift.annotation.Nullable
  public java.util.List<Phone> getPhones() {
    return this.phones;
  }

  /**
   * Phones
   */
  public void setPhones(@org.apache.thrift.annotation.Nullable java.util.List<Phone> phones) {
    this.phones = phones;
  }

  public void unsetPhones() {
    this.phones = null;
  }

  /** Returns true if field phones is set (has been assigned a value) and false otherwise */
  public boolean isSetPhones() {
    return this.phones != null;
  }

  public void setPhonesIsSet(boolean value) {
    if (!value) {
      this.phones = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.String)value);
      }
      break;

    case FIRST_NAME:
      if (value == null) {
        unsetFirstName();
      } else {
        setFirstName((java.lang.String)value);
      }
      break;

    case LAST_NAME:
      if (value == null) {
        unsetLastName();
      } else {
        setLastName((java.lang.String)value);
      }
      break;

    case BIRTH_DATE:
      if (value == null) {
        unsetBirthDate();
      } else {
        setBirthDate((java.lang.Long)value);
      }
      break;

    case EMAIL:
      if (value == null) {
        unsetEmail();
      } else {
        setEmail((java.lang.String)value);
      }
      break;

    case ADDRESS:
      if (value == null) {
        unsetAddress();
      } else {
        setAddress((Address)value);
      }
      break;

    case PHONES:
      if (value == null) {
        unsetPhones();
      } else {
        setPhones((java.util.List<Phone>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case FIRST_NAME:
      return getFirstName();

    case LAST_NAME:
      return getLastName();

    case BIRTH_DATE:
      return getBirthDate();

    case EMAIL:
      return getEmail();

    case ADDRESS:
      return getAddress();

    case PHONES:
      return getPhones();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case FIRST_NAME:
      return isSetFirstName();
    case LAST_NAME:
      return isSetLastName();
    case BIRTH_DATE:
      return isSetBirthDate();
    case EMAIL:
      return isSetEmail();
    case ADDRESS:
      return isSetAddress();
    case PHONES:
      return isSetPhones();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Customer)
      return this.equals((Customer)that);
    return false;
  }

  public boolean equals(Customer that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_firstName = true && this.isSetFirstName();
    boolean that_present_firstName = true && that.isSetFirstName();
    if (this_present_firstName || that_present_firstName) {
      if (!(this_present_firstName && that_present_firstName))
        return false;
      if (!this.firstName.equals(that.firstName))
        return false;
    }

    boolean this_present_lastName = true && this.isSetLastName();
    boolean that_present_lastName = true && that.isSetLastName();
    if (this_present_lastName || that_present_lastName) {
      if (!(this_present_lastName && that_present_lastName))
        return false;
      if (!this.lastName.equals(that.lastName))
        return false;
    }

    boolean this_present_birthDate = true;
    boolean that_present_birthDate = true;
    if (this_present_birthDate || that_present_birthDate) {
      if (!(this_present_birthDate && that_present_birthDate))
        return false;
      if (this.birthDate != that.birthDate)
        return false;
    }

    boolean this_present_email = true && this.isSetEmail();
    boolean that_present_email = true && that.isSetEmail();
    if (this_present_email || that_present_email) {
      if (!(this_present_email && that_present_email))
        return false;
      if (!this.email.equals(that.email))
        return false;
    }

    boolean this_present_address = true && this.isSetAddress();
    boolean that_present_address = true && that.isSetAddress();
    if (this_present_address || that_present_address) {
      if (!(this_present_address && that_present_address))
        return false;
      if (!this.address.equals(that.address))
        return false;
    }

    boolean this_present_phones = true && this.isSetPhones();
    boolean that_present_phones = true && that.isSetPhones();
    if (this_present_phones || that_present_phones) {
      if (!(this_present_phones && that_present_phones))
        return false;
      if (!this.phones.equals(that.phones))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetId()) ? 131071 : 524287);
    if (isSetId())
      hashCode = hashCode * 8191 + id.hashCode();

    hashCode = hashCode * 8191 + ((isSetFirstName()) ? 131071 : 524287);
    if (isSetFirstName())
      hashCode = hashCode * 8191 + firstName.hashCode();

    hashCode = hashCode * 8191 + ((isSetLastName()) ? 131071 : 524287);
    if (isSetLastName())
      hashCode = hashCode * 8191 + lastName.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(birthDate);

    hashCode = hashCode * 8191 + ((isSetEmail()) ? 131071 : 524287);
    if (isSetEmail())
      hashCode = hashCode * 8191 + email.hashCode();

    hashCode = hashCode * 8191 + ((isSetAddress()) ? 131071 : 524287);
    if (isSetAddress())
      hashCode = hashCode * 8191 + address.hashCode();

    hashCode = hashCode * 8191 + ((isSetPhones()) ? 131071 : 524287);
    if (isSetPhones())
      hashCode = hashCode * 8191 + phones.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Customer other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFirstName()).compareTo(other.isSetFirstName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFirstName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.firstName, other.firstName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLastName()).compareTo(other.isSetLastName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastName, other.lastName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetBirthDate()).compareTo(other.isSetBirthDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBirthDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.birthDate, other.birthDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetEmail()).compareTo(other.isSetEmail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.email, other.email);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAddress()).compareTo(other.isSetAddress());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAddress()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.address, other.address);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPhones()).compareTo(other.isSetPhones());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPhones()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.phones, other.phones);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Customer(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("firstName:");
    if (this.firstName == null) {
      sb.append("null");
    } else {
      sb.append(this.firstName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("lastName:");
    if (this.lastName == null) {
      sb.append("null");
    } else {
      sb.append(this.lastName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("birthDate:");
    sb.append(this.birthDate);
    first = false;
    if (!first) sb.append(", ");
    sb.append("email:");
    if (this.email == null) {
      sb.append("null");
    } else {
      sb.append(this.email);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("address:");
    if (this.address == null) {
      sb.append("null");
    } else {
      sb.append(this.address);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("phones:");
    if (this.phones == null) {
      sb.append("null");
    } else {
      sb.append(this.phones);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (address != null) {
      address.validate();
    }
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

  private static class CustomerStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public CustomerStandardScheme getScheme() {
      return new CustomerStandardScheme();
    }
  }

  private static class CustomerStandardScheme extends org.apache.thrift.scheme.StandardScheme<Customer> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Customer struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FIRST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.firstName = iprot.readString();
              struct.setFirstNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LAST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.lastName = iprot.readString();
              struct.setLastNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // BIRTH_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.birthDate = iprot.readI64();
              struct.setBirthDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // EMAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.email = iprot.readString();
              struct.setEmailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ADDRESS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.address = new Address();
              struct.address.read(iprot);
              struct.setAddressIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PHONES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.phones = new java.util.ArrayList<Phone>(_list8.size);
                @org.apache.thrift.annotation.Nullable Phone _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new Phone();
                  _elem9.read(iprot);
                  struct.phones.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setPhonesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Customer struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.firstName != null) {
        oprot.writeFieldBegin(FIRST_NAME_FIELD_DESC);
        oprot.writeString(struct.firstName);
        oprot.writeFieldEnd();
      }
      if (struct.lastName != null) {
        oprot.writeFieldBegin(LAST_NAME_FIELD_DESC);
        oprot.writeString(struct.lastName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(BIRTH_DATE_FIELD_DESC);
      oprot.writeI64(struct.birthDate);
      oprot.writeFieldEnd();
      if (struct.email != null) {
        oprot.writeFieldBegin(EMAIL_FIELD_DESC);
        oprot.writeString(struct.email);
        oprot.writeFieldEnd();
      }
      if (struct.address != null) {
        oprot.writeFieldBegin(ADDRESS_FIELD_DESC);
        struct.address.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.phones != null) {
        oprot.writeFieldBegin(PHONES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.phones.size()));
          for (Phone _iter11 : struct.phones)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CustomerTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public CustomerTupleScheme getScheme() {
      return new CustomerTupleScheme();
    }
  }

  private static class CustomerTupleScheme extends org.apache.thrift.scheme.TupleScheme<Customer> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Customer struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetFirstName()) {
        optionals.set(1);
      }
      if (struct.isSetLastName()) {
        optionals.set(2);
      }
      if (struct.isSetBirthDate()) {
        optionals.set(3);
      }
      if (struct.isSetEmail()) {
        optionals.set(4);
      }
      if (struct.isSetAddress()) {
        optionals.set(5);
      }
      if (struct.isSetPhones()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetFirstName()) {
        oprot.writeString(struct.firstName);
      }
      if (struct.isSetLastName()) {
        oprot.writeString(struct.lastName);
      }
      if (struct.isSetBirthDate()) {
        oprot.writeI64(struct.birthDate);
      }
      if (struct.isSetEmail()) {
        oprot.writeString(struct.email);
      }
      if (struct.isSetAddress()) {
        struct.address.write(oprot);
      }
      if (struct.isSetPhones()) {
        {
          oprot.writeI32(struct.phones.size());
          for (Phone _iter12 : struct.phones)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Customer struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.firstName = iprot.readString();
        struct.setFirstNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.lastName = iprot.readString();
        struct.setLastNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.birthDate = iprot.readI64();
        struct.setBirthDateIsSet(true);
      }
      if (incoming.get(4)) {
        struct.email = iprot.readString();
        struct.setEmailIsSet(true);
      }
      if (incoming.get(5)) {
        struct.address = new Address();
        struct.address.read(iprot);
        struct.setAddressIsSet(true);
      }
      if (incoming.get(6)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.phones = new java.util.ArrayList<Phone>(_list13.size);
          @org.apache.thrift.annotation.Nullable Phone _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new Phone();
            _elem14.read(iprot);
            struct.phones.add(_elem14);
          }
        }
        struct.setPhonesIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

