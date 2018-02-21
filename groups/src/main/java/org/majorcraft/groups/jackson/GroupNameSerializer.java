package org.majorcraft.groups.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import org.majorcraft.groups.model.Group;

import java.io.IOException;

/**
 * A Serializer witch just serializes the GroupId of a Group for the inheritance property
 */
public class GroupNameSerializer extends JsonSerializer<Group> {

    @Override
    public void serialize(Group group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(group.getGroupId());
    }

    @Override
    public void serializeWithType(Group value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {



    }
}
