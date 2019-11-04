/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 * 
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package com.othermo.vorto.plugin.dittoskeleton.templates;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.core.api.model.datatype.DictionaryPropertyType;
import org.eclipse.vorto.core.api.model.datatype.Entity;
import org.eclipse.vorto.core.api.model.datatype.EnumLiteral;
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.datatype.Type;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.plugin.generator.InvocationContext;
import org.eclipse.vorto.plugin.generator.utils.IFileTemplate;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Template that creates a Postman Script (collection) containing the requests
 * to provision the Vorto modelled device in the Bosch IoT Suite
 */
@SuppressWarnings("all")
public class ThingSkeletonTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel model) {
    String _namespace = model.getNamespace();
    String _plus = (_namespace + "_");
    String _name = model.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "_");
    String _version = model.getVersion();
    String _plus_3 = (_plus_2 + _version);
    return (_plus_3 + "_skeleton.json");
  }
  
  @Override
  public String getPath(final InformationModel model) {
    return null;
  }
  
  @Override
  public String getContent(final InformationModel model, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\"thingId\": \"{{device-id}}\",");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\"policyID\": \"{{policy-id}}\",");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\"attributes\": {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\"definition\": \"");
    String _namespace = model.getNamespace();
    _builder.append(_namespace, "\t\t");
    _builder.append(":");
    String _name = model.getName();
    _builder.append(_name, "\t\t");
    _builder.append(":");
    String _version = model.getVersion();
    _builder.append(_version, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("\"className\": \"");
    String _displayname = model.getDisplayname();
    _builder.append(_displayname, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("\"classDescription\": \"");
    String _description = model.getDescription();
    _builder.append(_description, "\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("},");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\"features\": {");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = model.getProperties();
      boolean _hasElements = false;
      for(final FunctionblockProperty fbProperty : _properties) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        _builder.append("\t\t");
        _builder.append("\"");
        String _name_1 = fbProperty.getName();
        _builder.append(_name_1, "\t\t");
        _builder.append("\" : {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("\"definition\": [\"");
        String _namespace_1 = fbProperty.getType().getNamespace();
        _builder.append(_namespace_1, "\t\t\t");
        _builder.append(":");
        String _name_2 = fbProperty.getType().getName();
        _builder.append(_name_2, "\t\t\t");
        _builder.append(":");
        String _version_1 = fbProperty.getType().getVersion();
        _builder.append(_version_1, "\t\t\t");
        _builder.append("\"],");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("\"properties\": {");
        _builder.newLine();
        {
          if (((fbProperty.getType().getFunctionblock().getConfiguration() != null) && (!fbProperty.getType().getFunctionblock().getConfiguration().getProperties().isEmpty()))) {
            _builder.append("\t\t");
            _builder.append("\t\t");
            _builder.append("\"configuration\": {");
            _builder.newLine();
            {
              EList<Property> _properties_1 = fbProperty.getType().getFunctionblock().getConfiguration().getProperties();
              boolean _hasElements_1 = false;
              for(final Property configProperty : _properties_1) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(",", "\t\t\t\t\t");
                }
                _builder.append("\t\t");
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("\"");
                String _name_3 = configProperty.getName();
                _builder.append(_name_3, "\t\t\t\t\t");
                _builder.append("\" : ");
                {
                  boolean _isMultiplicity = configProperty.isMultiplicity();
                  if (_isMultiplicity) {
                    _builder.append("[");
                  }
                }
                {
                  PropertyType _type = configProperty.getType();
                  if ((_type instanceof PrimitivePropertyType)) {
                    PropertyType _type_1 = configProperty.getType();
                    Object _jsonPrimitive = this.getJsonPrimitive(((PrimitivePropertyType) _type_1));
                    _builder.append(_jsonPrimitive, "\t\t\t\t\t");
                  } else {
                    PropertyType _type_2 = configProperty.getType();
                    if ((_type_2 instanceof ObjectPropertyType)) {
                      PropertyType _type_3 = configProperty.getType();
                      String _jsonObjectType = this.getJsonObjectType(((ObjectPropertyType) _type_3));
                      _builder.append(_jsonObjectType, "\t\t\t\t\t");
                    } else {
                      PropertyType _type_4 = configProperty.getType();
                      CharSequence _jsonDictionaryType = this.getJsonDictionaryType(((DictionaryPropertyType) _type_4));
                      _builder.append(_jsonDictionaryType, "\t\t\t\t\t");
                    }
                  }
                }
                {
                  boolean _isMultiplicity_1 = configProperty.isMultiplicity();
                  if (_isMultiplicity_1) {
                    _builder.append("]");
                  }
                }
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t\t");
            _builder.append("\t\t");
            _builder.append("}");
            {
              if (((fbProperty.getType().getFunctionblock().getStatus() != null) && (!fbProperty.getType().getFunctionblock().getStatus().getProperties().isEmpty()))) {
                _builder.append(",");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
        {
          if (((fbProperty.getType().getFunctionblock().getStatus() != null) && (!fbProperty.getType().getFunctionblock().getStatus().getProperties().isEmpty()))) {
            _builder.append("\t\t");
            _builder.append("\t\t");
            _builder.append("\"status\": {");
            _builder.newLine();
            {
              EList<Property> _properties_2 = fbProperty.getType().getFunctionblock().getStatus().getProperties();
              boolean _hasElements_2 = false;
              for(final Property statusProperty : _properties_2) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate(",", "\t\t\t\t\t");
                }
                _builder.append("\t\t");
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("\"");
                String _name_4 = statusProperty.getName();
                _builder.append(_name_4, "\t\t\t\t\t");
                _builder.append("\" : ");
                {
                  boolean _isMultiplicity_2 = statusProperty.isMultiplicity();
                  if (_isMultiplicity_2) {
                    _builder.append("[");
                  }
                }
                {
                  PropertyType _type_5 = statusProperty.getType();
                  if ((_type_5 instanceof PrimitivePropertyType)) {
                    PropertyType _type_6 = statusProperty.getType();
                    Object _jsonPrimitive_1 = this.getJsonPrimitive(((PrimitivePropertyType) _type_6));
                    _builder.append(_jsonPrimitive_1, "\t\t\t\t\t");
                  } else {
                    PropertyType _type_7 = statusProperty.getType();
                    if ((_type_7 instanceof ObjectPropertyType)) {
                      PropertyType _type_8 = statusProperty.getType();
                      String _jsonObjectType_1 = this.getJsonObjectType(((ObjectPropertyType) _type_8));
                      _builder.append(_jsonObjectType_1, "\t\t\t\t\t");
                    } else {
                      PropertyType _type_9 = statusProperty.getType();
                      CharSequence _jsonDictionaryType_1 = this.getJsonDictionaryType(((DictionaryPropertyType) _type_9));
                      _builder.append(_jsonDictionaryType_1, "\t\t\t\t\t");
                    }
                  }
                }
                {
                  boolean _isMultiplicity_3 = statusProperty.isMultiplicity();
                  if (_isMultiplicity_3) {
                    _builder.append("]");
                  }
                }
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t\t");
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  public CharSequence getJsonDictionaryType(final DictionaryPropertyType propertyType) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\"key\" : \"value\"");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String getJsonObjectType(final ObjectPropertyType propertyType) {
    Type _type = propertyType.getType();
    if ((_type instanceof org.eclipse.vorto.core.api.model.datatype.Enum)) {
      Type _type_1 = propertyType.getType();
      EList<EnumLiteral> literals = ((org.eclipse.vorto.core.api.model.datatype.Enum) _type_1).getEnums();
      boolean _isEmpty = literals.isEmpty();
      if (_isEmpty) {
        return "\"\"";
      } else {
        String _name = literals.get(0).getName();
        String _plus = ("\"" + _name);
        return (_plus + "\"");
      }
    } else {
      Type _type_2 = propertyType.getType();
      return this.getEntityJson(((Entity) _type_2)).toString();
    }
  }
  
  public CharSequence getEntityJson(final Entity entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    {
      EList<Property> _properties = entity.getProperties();
      boolean _hasElements = false;
      for(final Property property : _properties) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t");
        }
        _builder.append("\t");
        _builder.append("\"");
        String _name = property.getName();
        _builder.append(_name, "\t");
        _builder.append("\" : ");
        {
          PropertyType _type = property.getType();
          if ((_type instanceof PrimitivePropertyType)) {
            PropertyType _type_1 = property.getType();
            Object _jsonPrimitive = this.getJsonPrimitive(((PrimitivePropertyType) _type_1));
            _builder.append(_jsonPrimitive, "\t");
          } else {
            PropertyType _type_2 = property.getType();
            String _jsonObjectType = this.getJsonObjectType(((ObjectPropertyType) _type_2));
            _builder.append(_jsonObjectType, "\t");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public Object getJsonPrimitive(final PrimitivePropertyType propertyType) {
    PrimitiveType _type = propertyType.getType();
    boolean _tripleEquals = (_type == PrimitiveType.BASE64_BINARY);
    if (_tripleEquals) {
      return "\"\"";
    } else {
      PrimitiveType _type_1 = propertyType.getType();
      boolean _tripleEquals_1 = (_type_1 == PrimitiveType.BOOLEAN);
      if (_tripleEquals_1) {
        return Boolean.valueOf(false);
      } else {
        PrimitiveType _type_2 = propertyType.getType();
        boolean _tripleEquals_2 = (_type_2 == PrimitiveType.BYTE);
        if (_tripleEquals_2) {
          return "\"\"";
        } else {
          PrimitiveType _type_3 = propertyType.getType();
          boolean _tripleEquals_3 = (_type_3 == PrimitiveType.DATETIME);
          if (_tripleEquals_3) {
            return "\"2019-04-01T18:25:43-00:00\"";
          } else {
            PrimitiveType _type_4 = propertyType.getType();
            boolean _tripleEquals_4 = (_type_4 == PrimitiveType.DOUBLE);
            if (_tripleEquals_4) {
              return Double.valueOf(0.0);
            } else {
              PrimitiveType _type_5 = propertyType.getType();
              boolean _tripleEquals_5 = (_type_5 == PrimitiveType.FLOAT);
              if (_tripleEquals_5) {
                return Double.valueOf(0.0);
              } else {
                PrimitiveType _type_6 = propertyType.getType();
                boolean _tripleEquals_6 = (_type_6 == PrimitiveType.INT);
                if (_tripleEquals_6) {
                  return Integer.valueOf(0);
                } else {
                  PrimitiveType _type_7 = propertyType.getType();
                  boolean _tripleEquals_7 = (_type_7 == PrimitiveType.LONG);
                  if (_tripleEquals_7) {
                    return Integer.valueOf(0);
                  } else {
                    PrimitiveType _type_8 = propertyType.getType();
                    boolean _tripleEquals_8 = (_type_8 == PrimitiveType.SHORT);
                    if (_tripleEquals_8) {
                      return Integer.valueOf(0);
                    } else {
                      return "\"\"";
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
