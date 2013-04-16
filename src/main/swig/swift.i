%module swift
%{
#include "swift.h"
#include "compat.h"
%}

%inline %{
extern void Start(const char *sz_bind_addr, const char *sz_filename, const char *sz_tracker_addr, const char *sz_root_hash);
extern const char* GetRootHash(const char *sz_filename);
%}
