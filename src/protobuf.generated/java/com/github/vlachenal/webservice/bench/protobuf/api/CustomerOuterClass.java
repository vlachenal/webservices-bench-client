// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer.proto

package com.github.vlachenal.webservice.bench.protobuf.api;

public final class CustomerOuterClass {
  private CustomerOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_webservicebench_Customer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_webservicebench_Customer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_webservicebench_Customer_Address_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_webservicebench_Customer_Address_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_webservicebench_Customer_Phone_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_webservicebench_Customer_Phone_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_webservicebench_ListAllResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_webservicebench_ListAllResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_webservicebench_TestSuite_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_webservicebench_TestSuite_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_webservicebench_TestSuite_ClientCall_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_webservicebench_TestSuite_ClientCall_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016customer.proto\022\017webservicebench\"\223\003\n\010Cu" +
      "stomer\022\n\n\002id\030\001 \001(\t\022\021\n\tfirstName\030\002 \001(\t\022\020\n" +
      "\010lastName\030\003 \001(\t\022\021\n\tbirthDate\030\004 \001(\003\022\r\n\005em" +
      "ail\030\005 \001(\t\0222\n\007address\030\006 \001(\0132!.webserviceb" +
      "ench.Customer.Address\022/\n\006phones\030\007 \003(\0132\037." +
      "webservicebench.Customer.Phone\032H\n\007Addres" +
      "s\022\r\n\005lines\030\001 \003(\t\022\017\n\007zipCode\030\002 \001(\t\022\014\n\004cit" +
      "y\030\003 \001(\t\022\017\n\007country\030\004 \001(\t\032\204\001\n\005Phone\022\016\n\006nu" +
      "mber\030\001 \001(\t\0227\n\004type\030\002 \001(\0162).webserviceben" +
      "ch.Customer.Phone.PhoneType\"2\n\tPhoneType" +
      "\022\013\n\007UNKNOWN\020\000\022\014\n\010LANDLINE\020\001\022\n\n\006MOBILE\020\002\"" +
      "?\n\017ListAllResponse\022,\n\tcustomers\030\001 \003(\0132\031." +
      "webservicebench.Customer\"\250\003\n\tTestSuite\022\n" +
      "\n\002id\030\001 \001(\t\022\020\n\010nbThread\030\002 \001(\005\022\023\n\013compress" +
      "ion\030\003 \001(\t\022\013\n\003cpu\030\004 \001(\t\022\016\n\006memory\030\005 \001(\t\022\013" +
      "\n\003jvm\030\006 \001(\t\022\016\n\006vendor\030\007 \001(\t\022\020\n\010osFamily\030" +
      "\010 \001(\t\022\021\n\tosVersion\030\t \001(\t\022\020\n\010protocol\030\n \001" +
      "(\t\022\017\n\007comment\030\013 \001(\t\0224\n\005calls\030\014 \003(\0132%.web" +
      "servicebench.TestSuite.ClientCall\022\'\n\006map" +
      "per\030\r \001(\0162\027.webservicebench.Mapper\032\206\001\n\nC" +
      "lientCall\022\022\n\nrequestSeq\030\001 \001(\005\022\020\n\010protoco" +
      "l\030\002 \001(\t\022\016\n\006method\030\003 \001(\t\022\023\n\013clientStart\030\004" +
      " \001(\003\022\021\n\tclientEnd\030\005 \001(\003\022\n\n\002ok\030\006 \001(\010\022\016\n\006e" +
      "rrMsg\030\007 \001(\t*.\n\006Mapper\022\n\n\006MANUAL\020\000\022\t\n\005DOZ" +
      "ER\020\001\022\r\n\tMAPSTRUCT\020\002B6\n2com.github.vlache" +
      "nal.webservice.bench.protobuf.apiP\001b\006pro" +
      "to3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_webservicebench_Customer_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_webservicebench_Customer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_webservicebench_Customer_descriptor,
        new java.lang.String[] { "Id", "FirstName", "LastName", "BirthDate", "Email", "Address", "Phones", });
    internal_static_webservicebench_Customer_Address_descriptor =
      internal_static_webservicebench_Customer_descriptor.getNestedTypes().get(0);
    internal_static_webservicebench_Customer_Address_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_webservicebench_Customer_Address_descriptor,
        new java.lang.String[] { "Lines", "ZipCode", "City", "Country", });
    internal_static_webservicebench_Customer_Phone_descriptor =
      internal_static_webservicebench_Customer_descriptor.getNestedTypes().get(1);
    internal_static_webservicebench_Customer_Phone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_webservicebench_Customer_Phone_descriptor,
        new java.lang.String[] { "Number", "Type", });
    internal_static_webservicebench_ListAllResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_webservicebench_ListAllResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_webservicebench_ListAllResponse_descriptor,
        new java.lang.String[] { "Customers", });
    internal_static_webservicebench_TestSuite_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_webservicebench_TestSuite_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_webservicebench_TestSuite_descriptor,
        new java.lang.String[] { "Id", "NbThread", "Compression", "Cpu", "Memory", "Jvm", "Vendor", "OsFamily", "OsVersion", "Protocol", "Comment", "Calls", "Mapper", });
    internal_static_webservicebench_TestSuite_ClientCall_descriptor =
      internal_static_webservicebench_TestSuite_descriptor.getNestedTypes().get(0);
    internal_static_webservicebench_TestSuite_ClientCall_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_webservicebench_TestSuite_ClientCall_descriptor,
        new java.lang.String[] { "RequestSeq", "Protocol", "Method", "ClientStart", "ClientEnd", "Ok", "ErrMsg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
