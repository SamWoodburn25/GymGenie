package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new edu.quinnipiac.ser210.myapplication.DataBinderMapperImpl());
  }
}
